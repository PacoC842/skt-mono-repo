package com.skytouch.service;

import com.skytouch.model.Book;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface SyncKafkaService {
    //String get(String text) throws TimeoutException;
    List<Book> getKafkaUtil() throws Exception;


}

