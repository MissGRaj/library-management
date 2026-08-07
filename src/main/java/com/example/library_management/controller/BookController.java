package com.example.library_management.controller;
import com.example.library_management.dto.response.BookResponse;
import com.example.library_management.entity.Book;
import com.example.library_management.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book){
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);

    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook){
        Book book = bookService.updateBook(id, updatedBook);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Book book = bookService.deleteBook(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String author,
                                                  @RequestParam(required = false) String title){
        if(author != null && title != null){
            return ResponseEntity.ok(bookService.searchBooksByAuthorAndTitleIgnoreCase(author, title));
        }

        if(author != null) {
            return ResponseEntity.ok(bookService.searchBooksByAuthor(author));
        }

        if(title != null) {
            return ResponseEntity.ok(bookService.searchBooksByTitleIgnoreCase(title));
        }
        return ResponseEntity.badRequest().build();
    }

}
