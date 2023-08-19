package com.aicheung.tdd.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aicheung.tdd.library.entity.Book;
import com.aicheung.tdd.library.service.BookService;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;
    
    @PostMapping("checkout")
    public ResponseEntity<Book> checkout(@RequestBody Book book) {
        Book response = bookService.checkOut(book);
        return ResponseEntity.ok(response);
    }
}
