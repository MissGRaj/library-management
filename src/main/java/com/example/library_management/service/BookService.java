package com.example.library_management.service;
import com.example.library_management.dto.response.BookResponse;
import com.example.library_management.entity.Book;
import com.example.library_management.exception.BookNotFoundException;
import com.example.library_management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(){

        return bookRepository.findAll();
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public BookResponse getBookById(Long id){

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book Not Found with id " + id));
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor()
        );
    }

    public Book updateBook(Long id, Book updatedBook){

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book Not Found with id " + id));
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        return bookRepository.save(existingBook);

    }

    public Book deleteBook(Long id){

        Book book = bookRepository.findById(id)
                .orElseThrow(()
                -> new BookNotFoundException("Book Not Found With id " + id));
        bookRepository.deleteById(id);
        return book;
    }

    public List<Book> searchBooksByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public List<Book> searchBooksByTitleIgnoreCase(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchBooksByAuthorAndTitleIgnoreCase(String author, String title){
        return bookRepository.findByAuthorAndTitleContainingIgnoreCase(author, title);
    }

}
