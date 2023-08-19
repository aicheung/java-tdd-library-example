package com.aicheung.tdd.library.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aicheung.tdd.library.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{

    List<Book> findByAuthor(String string);
    
}
