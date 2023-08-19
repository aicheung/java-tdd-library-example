package com.aicheung.tdd.library;

import java.util.UUID;

import com.aicheung.tdd.library.entity.Book;
import com.aicheung.tdd.library.repository.BookRepository;

public class BookTestUtil {
    
    public static Book setupData(BookRepository bookRepository) {
        bookRepository.deleteAll();
        Book b = new Book("Spring", "Eric", "available", 0);
        b.setId(UUID.randomUUID());
        b = bookRepository.save(b);
        return b;
    }
}
