package com.skytouch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booklist {
    private List<Book> books = new ArrayList<>();

    @Override
    public String toString() {
        return "Booklist{" +
                "books=" + books +
                '}';
    }


}

