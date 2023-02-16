package com.skytouch.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@NamedStoredProcedureQuery(name = "Book.createBook",
        procedureName = "CREATE_BOOK", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "id_in", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "name_in", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "author_in", type = String.class)
        /*@StoredProcedureParameter(mode = ParameterMode.OUT, name = "count_out", type = Integer.class)*/})
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;


}
