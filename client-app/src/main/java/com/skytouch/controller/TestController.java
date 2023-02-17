package com.skytouch.controller;

import com.skytouch.model.Booklist;
import com.skytouch.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    BookService bookService;

    public TestController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping({"/home-1"})
    public String getHello() {
        return "hello";
    }

    @GetMapping({"/books-rest"})
    public Booklist getBooks() {
        bookService.getBooksRest();
        return bookService.getBooksRest();
    }

}
