package com.skytouch.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skytouch.kafka.KafkaMessageSender;
import com.skytouch.model.Booklist;
import com.skytouch.model.KafkaMessage;
import com.skytouch.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class Consumer {

    private final KafkaMessageSender kafkaMessageSender;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private BookService bookService;

    public Consumer(KafkaMessageSender kafkaMessageSender, BookService bookService) {
        this.kafkaMessageSender = kafkaMessageSender;
        this.bookService = bookService;
    }


    @KafkaListener(topics = "habr-topic", groupId = "demo")
    public void listenGroupFoo(String rqId) throws JsonProcessingException {
        log.info("request from client {}", rqId);
        rqId = rqId.replace("\"", "");
        rqId = rqId.replace("\"", "");
        UUID sameUuid = UUID.fromString(rqId);

        Booklist booklist = bookService.getAll();

//        kafkaMessageSender.send(sameUuid, new KafkaMessage<>(bookService.getAll().toString()));
        kafkaMessageSender.send(sameUuid, new KafkaMessage<>(objectMapper.writeValueAsString(bookService.getAll().getBooks())));


        System.out.println("Received Message in group foo: " + rqId.toString() + "xoxoxoxo");
    }

}
