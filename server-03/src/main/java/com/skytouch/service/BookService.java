package com.skytouch.service;


import com.skytouch.model.Book;
import com.skytouch.model.Booklist;
import com.skytouch.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) {
        bookRepository.createBook(book.getId(), book.getName(), book.getAuthor());
    }

    public Booklist getAll() {
        Booklist list = new Booklist();
        List<Book> books = bookRepository.getAll();
        for (Book e : books) {
            list.getBooks().add(e);
        }
        return list;
    }
}
