package com.skytouch.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skytouch.model.Book;
import com.skytouch.model.RequestDto;
import com.skytouch.senderreceiver.SenderReceiverMap;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
public class SyncKafkaServiceImp implements SyncKafkaService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    KafkaTemplate kafkaTemplate;
    private SenderReceiverMap<UUID, String> senderReceiverMap;
    @Value("${endpointServer}")
    private String endpointServer;
    @Value("${timeout:0}")
    private Long timeout;

    private String hbrTopic = "habr-topic";

    public SyncKafkaServiceImp(SenderReceiverMap<UUID, String> senderReceiverMap) {
        this.senderReceiverMap = senderReceiverMap;
    }

    public String get(String text) throws TimeoutException {

        UUID requestId = UUID.randomUUID();
        while (senderReceiverMap.containsKey(requestId)) {
            requestId = UUID.randomUUID();
        }

        String responseFromServer = this.sendText(requestId, text);

        System.out.println("REST response from server: " + responseFromServer);

        Thread thread = senderReceiverMap.add(requestId, timeout);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String responseKafka;
        try {
            responseKafka = senderReceiverMap.get(requestId).getData();
        } catch (TimeoutException e) {
            throw e;
        } finally {
            senderReceiverMap.remove(requestId);
        }
        return responseKafka;
    }

    public List<Book> getKafkaUtil() throws TimeoutException {
        UUID requestId = UUID.randomUUID();
        while (senderReceiverMap.containsKey(requestId)) {
            requestId = UUID.randomUUID();
        }

        sendTextThruKafka(requestId.toString());

        System.out.println("UUID: " + requestId.toString()/*responseFromServer*/);

        Thread thread = senderReceiverMap.add(requestId, timeout);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String responseKafka;
        try {
            responseKafka = senderReceiverMap.get(requestId).getData();
        } catch (TimeoutException e) {
            throw e;
        } finally {
            senderReceiverMap.remove(requestId);
        }
        List<Book> books;
        try {
            books = objectMapper.readValue(responseKafka, List.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    private String sendText(final UUID requestId, final String text) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(text + "pl2j407407");

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJsonStr = null;

        try {
            requestJsonStr = new ObjectMapper().writeValueAsString(new RequestDto(requestId, text));
            System.out.println(requestJsonStr + "requestJsonStr pl2j407");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpEntity<String> request = new HttpEntity<>(requestJsonStr, headers);
        return restTemplate.postForObject(endpointServer, request, String.class);
    }

    private String sendTextThruKafka(String requestId) {
        ProducerRecord<String, String> record = new ProducerRecord("habr-topic", "test");
        record.headers().add(new RecordHeader("kafka_replyTopic", hbrTopic.getBytes()));


        kafkaTemplate.send(hbrTopic, requestId);
        return "message sent";
    }
}
