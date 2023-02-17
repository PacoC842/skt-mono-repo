package com.skytouch.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skytouch.model.Book;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


public class CustomSerializer implements Serializer<Book> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, Book book) {
        try {
            if (book == null) {
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            return objectMapper.writeValueAsBytes(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public void close() {

    }
}
