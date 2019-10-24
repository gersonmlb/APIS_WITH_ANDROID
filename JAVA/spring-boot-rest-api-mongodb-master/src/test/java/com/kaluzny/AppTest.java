package com.kaluzny;

import com.kaluzny.domain.Users;
import com.kaluzny.domain.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    private UsersRepository repository;

    @Autowired
    public void setRepository(UsersRepository repository) {
        this.repository = repository;
    }

    @Test
    public void createBook() {
    	Users book = new Users(3, "Java 8 in Action", "Programming");
       /* Book savedBook = repository.save(book);
        Book newBook = repository.findOne(savedBook.Id());
        assertEquals("Java 8 in Action", newBook.getName());
        assertEquals("Programming", newBook.getDescription());*/
    }

    @Test
    public void findAllBooks() {
        List<Users> books = repository.findAll();
        assertNotNull(books);
        assertTrue(!books.isEmpty());
    }

    @Test
    public void findBookById() {
    	Users book = repository.findOne(1);
        assertNotNull(book);
    }

    @Test
    public void deleteBookWithId() {
    	Users book = repository.findOne(1);
        repository.delete(book);
        assertNotNull(book);
    }
}