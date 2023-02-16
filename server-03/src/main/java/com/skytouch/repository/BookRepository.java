package com.skytouch.repository;

import com.skytouch.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "CALL FIND_ALL_BOOKS();", nativeQuery = true)
    List<Book> getAll();

    @Procedure(name = "Book.createBook")
    void createBook(@Param("id_in") Integer id, @Param("name_in") String name, @Param("author_in") String author);

}
