package com.example.library_management.service;

import com.example.library_management.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();

    public BookService(){
        books.add(new Book(1L, "Clean Code",
                "Robert C. Martin"));
        books.add(new Book(2L, "Effective Java",
                "Joshua Bloch"));
    }

    public List<Book> getBooks(){
        return books;
    }

    public void addBooks(Book book){
        books.add(book);
    }
}
