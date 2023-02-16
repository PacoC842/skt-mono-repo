package com.skytouch.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skytouch.model.Book;
import com.skytouch.model.Booklist;
import com.skytouch.model.RequestDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.Duration;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class BookService {

    @Value("${endpointServer}")
    private String endpointServer;
    private final String requestTopic = "request-topic";
    private final String requestReplyTopic = "requestreply-topic";

//    ReplyingKafkaTemplate replyingKafkaTemplate;
//
//    public List<Book> sendAndReceive() throws InterruptedException, ExecutionException {
//        Booklist booklist = new Booklist();
//        // create producer record
//        ProducerRecord<String, Booklist> record = new ProducerRecord(requestTopic, booklist);
//        // set reply topic in header
//        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));
//        // post in kafka topic
//        RequestReplyFuture<String, Booklist, Booklist> sendAndReceive = replyingKafkaTemplate.sendAndReceive(record, Duration.ofSeconds(15));
//        // confirm if producer produced successfully
//        ListenableFuture<SendResult<String, Booklist>> sendResultGet = sendAndReceive.getSendFuture();
//        SendResult<String, Booklist> sendResult = sendResultGet.get();
//        //SendResult<String, Booklist> sendResult = sendAndReceive.getSendFuture().get();
//        //print all headers
//        //sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));
//        // get consumer record
//        ConsumerRecord<String, Booklist> consumerRecord = sendAndReceive.get();
//        // return consumer value
//        //print something
//        log.info("Response received!!!");
//        //////controller test
//        booklist = consumerRecord.value();
//        List<Book> list = booklist.getBooks();
//        return list;
//    }

    public Booklist getBooksRest(){
        UUID requestId = UUID.randomUUID();
//        while (senderReceiverMap.containsKey(requestId)) {
//            requestId = UUID.randomUUID();
//        }
        return this.GetAllFromServer(requestId, "post Call");
    }

    private Booklist GetAllFromServer(final UUID requestId, final String text) {
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
        return restTemplate.postForObject(endpointServer, request, Booklist.class);
    }
}
