package com.aicheung.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aicheung.tdd.library.entity.Book;
import com.aicheung.tdd.library.repository.BookRepository;
import com.aicheung.tdd.library.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Before
    public void setup() {
        BookTestUtil.setupData(bookRepository);
    }
    
    @Test
    public void shouldCheckOut() {
        List<Book> b = bookRepository.findByAuthor("Eric");
        Book book = bookService.checkOut(b.get(0));
        assertEquals("checkedOut", book.getStatus());
        assertEquals(1, book.getReadCount());
    }

    @Test
    public void shouldNotCheckOutAlreadyCheckedOutBook() {
        shouldCheckOut();
        List<Book> b = bookRepository.findByAuthor("Eric");
        Book book = bookService.checkOut(b.get(0));
        assertNull(book);
        b = bookRepository.findByAuthor("Eric");
        assertEquals(1, b.get(0).getReadCount());
    }
}
