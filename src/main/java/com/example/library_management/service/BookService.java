package com.example.library_management.service;
import com.example.library_management.dto.response.BookResponse;
import com.example.library_management.entity.Book;
import com.example.library_management.exception.BookNotFoundException;
import com.example.library_management.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Book> searchBooksByAuthor(String author, Pageable pageable){
        return bookRepository.findByAuthor(author, pageable);
    }

    public Page<Book> searchBooksByTitleIgnoreCase(String title, Pageable pageable){
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Page<Book> searchBooksByAuthorAndTitleIgnoreCase(
            String author,
            String title,
            Pageable pageable){
        return bookRepository.findByAuthorAndTitleContainingIgnoreCase(
                author,
                title,
                pageable);
    }

//    jpql
    public List<Book> searchBooksByAuthorJPQL(String author){
        return bookRepository.findBooksByAuthorJPQL(author);
    }

    public List<Book> searchBooksJPQL(String author, String title){
        return bookRepository.findBooksJPQL(author, title);
    }

//    pagination
    public Page<Book> searchBooks(Pageable pageable){
        return bookRepository.findAll(pageable);
    }
}
