package com.skytouch.controller;


import com.skytouch.model.Book;
import com.skytouch.model.Booklist;
import com.skytouch.model.RequestDto;
import com.skytouch.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
@Slf4j
public class RestController {

    BookService bookService;

    public RestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/test")
    public Booklist test(@RequestBody RequestDto request) {

        bookService.getAll();

        System.out.println("pl2j407 " + request);
        System.out.println("pl2j407 id" + request.getRequestId());
        System.out.println("pl2j407 date" + request.getData());

        return bookService.getAll();
    }

    @PostMapping("/create-book")
    public String createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return "request receieved";
    }
}
