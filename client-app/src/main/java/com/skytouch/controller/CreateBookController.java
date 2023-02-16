package com.skytouch.controller;


import com.skytouch.model.Book;
import com.skytouch.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class CreateBookController extends HttpServlet {


    @Autowired
    final KafkaTemplate kafkaTemplate;
    BookService bookService;

    public CreateBookController(KafkaTemplate kafkaTemplate, BookService bookService) {
        this.kafkaTemplate = kafkaTemplate;
        this.bookService = bookService;
    }


    @PostMapping(value = "/create-book-entry")
    public ResponseEntity<String> createBookA(HttpServletRequest request) {
        log.info("pl2j407");
        String idTmp =request.getParameter("lid");
        int id;
        try {
            id = Integer.valueOf(idTmp);
        } catch (Exception e){
            return new ResponseEntity<String>("<a href=\"http://localhost:5174/index.html\"><h1>Error processing request</h1></a>", HttpStatus.OK);
        }
        String name = request.getParameter("fname");
        String author = request.getParameter("lauthor");
        log.info("my parameters are {}, {}, {}", id, name, author);
        this.kafkaTemplate.send("create-entry-topic", new Book(id, name, author));
        return new ResponseEntity<String>("<a href=\"http://localhost:5174/index.html\"><h1>success</h1></a>", HttpStatus.OK);
    }
}
