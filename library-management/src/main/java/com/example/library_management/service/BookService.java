package com.example.library_management.service;

import com.example.library_management.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();
    private Long nextId = 3L;

    public BookService(){
        books.add(new Book(1L, "Clean Code",
                "Robert C. Martin"));
        books.add(new Book(2L, "Effective Java",
                "Joshua Bloch"));
    }

    public List<Book> getBooks(){
        return books;
    }

    public Book addBooks(Book book){
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    public Book getById(Long id){

        for(Book book : books){
            if(book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }
}
