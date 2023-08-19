package com.aicheung.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTest {

    @Autowired
    BookRepository bookRepository;

    @Before
    public void setup() {
        bookRepository.deleteAll();
    }
    
    @Test
    public void testCreate() {
        Book b = new Book("Spring", "Eric", "available", 999);
        b.setId(UUID.randomUUID());

        b = bookRepository.save(b);
        assertTrue(b.getId() != null);
        assertEquals("Eric", b.getAuthor());
        assertEquals("Spring", b.getName());
        assertEquals(999, b.getReadCount());
        assertEquals("available", b.getStatus());
    }

    @Test
    public void testRetrieve() {
        testCreate();
        List<Book> result = bookRepository.findByAuthor("Eric");
        assertTrue(result.size() > 0);
        assertEquals("Eric", result.get(0).getAuthor());
    }

    @Test
    public void testUpdate() {
        testCreate();
        List<Book> result = bookRepository.findByAuthor("Eric");
        int count = result.size();
        Book b = result.get(0);
        String uuid = b.getId().toString();
        b.setName("Good Book");
        b = bookRepository.save(b);
        result = bookRepository.findByAuthor("Eric");
        int countAfter = result.size();
        String uuidAfter = result.get(0).getId().toString();
        assertEquals(count, countAfter);
        assertEquals(uuid, uuidAfter);
        assertEquals("Good Book", result.get(0).getName());
    }

    @Test
    public void testDelete() {
        testCreate();
        List<Book> result = bookRepository.findByAuthor("Eric");
        bookRepository.delete(result.get(0));
        result = bookRepository.findByAuthor("Eric");
        assertEquals(0, result.size());
    }
}
