package com.skytouch.controller;//package com.company.app.controller;
//
//
//import com.company.app.model.Book;
//import com.company.app.repository.BookRepository;
//import com.company.app.service.BookService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@Slf4j
//public class TestController {
//
//    private final BookRepository bookRepository;
//
//    @Autowired
//    BookService bookService;
//
//    public TestController(BookRepository bookRepository, BookService bookService) {
//        this.bookRepository = bookRepository;
//        this.bookService = bookService;
//    }
//
//    @GetMapping("/db-test")
//    public List<Book> getBooks() {
//        List<Book> books = bookRepository.findAll();
//        return books;
//    }
//
//    @GetMapping("/db-test-stored-procedure")
//    public List<Book> getBooksStoredProc() {
//        List<Book> books = bookRepository.getAll();
//        return books;
//    }
//
//    @PostMapping("/createRecord")
//    public String createRecord(@RequestBody Book book) {
//        log.info("whats going on {}", book.toString());
//        bookRepository.createBook(book.getId(), book.getName(), book.getAuthor());
//        return "Record successfully created";
//    }
//}
