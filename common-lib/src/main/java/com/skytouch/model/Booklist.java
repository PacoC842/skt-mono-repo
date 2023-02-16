package com.skytouch.model;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Booklist {
    List<Book> books;


    @Override
    public String toString() {
        return "Booklist{" +
                "books=" + books +
                '}';
    }


}
