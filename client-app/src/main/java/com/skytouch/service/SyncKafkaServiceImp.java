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

    public List<Book> getKafkaUtil() throws Exception {
        UUID requestId = UUID.randomUUID();
        sendTextThruKafka(requestId.toString());
        Thread thread = senderReceiverMap.add(requestId, timeout);
        thread.start();
        thread.join();
        String responseKafka = senderReceiverMap.get(requestId).getData();
        return objectMapper.readValue(responseKafka, List.class);
    }

    private String sendTextThruKafka(String requestId) {
        ProducerRecord<String, String> record = new ProducerRecord("habr-topic", "test");
        record.headers().add(new RecordHeader("kafka_replyTopic", hbrTopic.getBytes()));
        kafkaTemplate.send(hbrTopic, requestId);
        return "message sent";
    }
}
