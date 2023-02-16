package com.skytouch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NamedStoredProcedureQuery(name = "Book.createBook",
        procedureName = "CREATE_BOOK", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "id_in", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "name_in", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "author_in", type = Integer.class)
        /*@StoredProcedureParameter(mode = ParameterMode.OUT, name = "count_out", type = Integer.class)*/})
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
