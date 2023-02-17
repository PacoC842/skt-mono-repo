package com.skytouch.controller;//package com.company.app.controller;

import com.skytouch.model.Book;
import com.skytouch.model.Booklist;
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
//
//    final KafkaTemplate kafkaTemplate;
//    BookService bookService;
//    @Autowired
//    ReplyingKafkaTemplate replyingKafkaTemplate;
//
//    public BookKafkaController(ReplyingKafkaTemplate replyingKafkaTemplate,
//                               KafkaTemplate kafkaTemplate, BookService bookService) {
//        this.replyingKafkaTemplate = replyingKafkaTemplate;
//        this.kafkaTemplate = kafkaTemplate;
//        this.bookService = bookService;
//    }
//
//    @RequestMapping("/books-2")
//    public String getAllBooksViaBroker(Model model) throws InterruptedException, ExecutionException {
//        List<Book> booklist = bookService.sendAndReceive();
//        model.addAttribute("books", booklist);
//        return "books";
//    }
//    @CrossOrigin
//    @RequestMapping("/books")
//    public ResponseEntity<List<Book>> getAllBooks() throws InterruptedException, ExecutionException {
//        List<Book> booklist = bookService.sendAndReceive();
//
//        /*List<Book> books = new ArrayList<>();
//        books.add(new Book(10,"book1","asdf"));
//        books.add(new Book(10,"book2","asdf"));
//        ResponseEntity<List<Book>> responseEntity = new ResponseEntity<>(books,HttpStatus.OK);*/
//        ResponseEntity<List<Book>> responseEntity = new ResponseEntity<>(booklist,HttpStatus.OK);
//        return responseEntity;
//    }



    @CrossOrigin
    @RequestMapping("/books-poc")
    public ResponseEntity<List<Book>> getAllBooks() throws InterruptedException, ExecutionException {
        // List<Book> booklist = bookService.sendAndReceive();

        List<Book> books = new ArrayList<>();
        books.add(new Book(10, "book1", "asdf"));
        books.add(new Book(10, "book2", "asdf"));
        ResponseEntity<List<Book>> responseEntity = new ResponseEntity<>(books, HttpStatus.OK);
        // ResponseEntity<List<Book>> responseEntity = new ResponseEntity<>(booklist,HttpStatus.OK);
        return responseEntity;
    }

    @CrossOrigin
    @GetMapping("/books")
    public ResponseEntity<List<Book>> test1() {

        List<Book> books = new ArrayList<>();
        books.add(new Book(10, "book1", "asdf"));
        books.add(new Book(10, "book2", "asdf"));
        try {
            books = syncKafkaService.getKafkaUtil();
        } catch (TimeoutException e) {
            return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
        }
        ResponseEntity<List<Book>> responseEntity = new ResponseEntity<>(books, HttpStatus.OK);
//         ResponseEntity<List<Book>> responseEntity = new ResponseEntity<>(booklist,HttpStatus.OK);
        return responseEntity;

//        return ResponseEntity.ok(result);
    }
}
