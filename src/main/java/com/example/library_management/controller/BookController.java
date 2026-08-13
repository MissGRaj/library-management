package com.example.library_management.controller;
import com.example.library_management.dto.request.BookSearchRequest;
import com.example.library_management.dto.response.BookResponse;
import com.example.library_management.entity.Book;
import com.example.library_management.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<Book>> searchBooks(
            BookSearchRequest request){

        return ResponseEntity.ok(bookService.searchBooks(request));
    }

//    jpql endpoints
    @GetMapping("/books/search/jpql")
    public ResponseEntity<List<Book>> searchBooksJPQL(@RequestParam String author){
        return ResponseEntity.ok(bookService.searchBooksByAuthorJPQL(author));
    }

    @GetMapping("/books/search/jpql1")
    public ResponseEntity<List<Book>> searchBooksJPQL1(@RequestParam String author,
                                                       @RequestParam String title){
        return ResponseEntity.ok(bookService.searchBooksJPQL(author, title));
    }

//    pagination endpoint
//    @GetMapping("/books_page")
//    public ResponseEntity<Page<Book>> getBooks(
//            @RequestParam int page,
//            @RequestParam int size,
//            @RequestParam String sortBy,
//            @RequestParam String direction){
//
//
//        return ResponseEntity.ok(bookService.searchBooks(pageable));
//    }

}
