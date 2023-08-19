package com.aicheung.tdd.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aicheung.tdd.library.entity.Book;
import com.aicheung.tdd.library.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book checkOut(Book book) {
        if("available".equals(book.getStatus())) {
            book.setStatus("checkedOut");
            book.setReadCount(book.getReadCount() + 1);
            book = bookRepository.save(book);
            return book;
        }
        return null;
    }
    
}
