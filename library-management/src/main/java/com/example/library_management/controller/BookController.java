package com.example.library_management.controller;

import com.example.library_management.entity.Book;
import com.example.library_management.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @PostMapping("/books")
    public Book addBooks(@RequestBody Book book){
        return bookService.addBooks(book);

    }

    @GetMapping("/books/{id}")
    public Book getById(@PathVariable Long id){
        return bookService.getById(id);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public Book deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }
}
