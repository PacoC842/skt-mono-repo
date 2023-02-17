package com.skytouch.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.skytouch.model.Book;
import com.skytouch.model.BookDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class CreateBookController {


    private KafkaTemplate kafkaTemplate;
    private ObjectMapper objectMapper;

    public CreateBookController(KafkaTemplate kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @CrossOrigin
    @PostMapping(value = "/create-book-entry")
    public String createBook(@RequestBody String bookDTO) throws IOException {
        log.info("pl2j407");
        log.info("Book json {}", bookDTO);
        BookDTO bookDto = objectMapper.readValue(bookDTO, BookDTO.class);
        String idTmp = bookDto.getLid();
        int id;
        try {
            id = Integer.valueOf(idTmp);
        } catch (Exception e) {
            log.error("An exception was thrown {}", e.getMessage());
            return "Error processing Request";
        }
        String name = bookDto.getFname();
        String author = bookDto.getLauthor();
        log.info("my parameters are {}, {}, {}", id, name, author);
        kafkaTemplate.send("create-entry-topic", new Book(id, name, author));
        return "success";
    }

}
