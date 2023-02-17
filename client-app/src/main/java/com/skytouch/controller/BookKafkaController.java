package com.skytouch.controller;//package com.company.app.controller;

import com.skytouch.model.Book;
import com.skytouch.service.SyncKafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Controller
@Slf4j
public class BookKafkaController {

    SyncKafkaService syncKafkaService;

    public BookKafkaController(SyncKafkaService syncKafkaService) {
        this.syncKafkaService = syncKafkaService;
    }

    @CrossOrigin
    @GetMapping("/books")
    public ResponseEntity<List<Book>> test1() {
        List<Book> books = new ArrayList<>();

        try {
            books = syncKafkaService.getKafkaUtil();
            log.info("books state {}", books);
        } catch (TimeoutException e) {
            log.info("getBooks() Timeout exception: {}", e.getMessage());
            books.add(new Book(0,"error","error"));
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        ResponseEntity<List<Book>> responseEntity = new ResponseEntity<>(books, HttpStatus.OK);

        log.info("books state 1 {}", books);
        return responseEntity;
    }
}
