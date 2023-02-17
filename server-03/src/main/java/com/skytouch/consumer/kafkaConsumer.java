package com.skytouch.consumer;

import com.google.gson.Gson;
import com.skytouch.model.Book;
import com.skytouch.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class kafkaConsumer {

    BookService bookService;

    public kafkaConsumer(BookService bookService) {
        this.bookService = bookService;
    }

    @KafkaListener(topics = "${kafka.topic.create-entry-topic}")
    public void listenGroupFoo(String book) {
        Gson g = new Gson();
        Book book1 = g.fromJson(book, Book.class);

        log.info(book.toString());
        System.out.println("Received Message in group demo: " + book);
        bookService.createBook(book1);
    }
}
