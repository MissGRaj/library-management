package com.example.library_management.service;
import com.example.library_management.dto.request.BookRequest;
import com.example.library_management.dto.request.BookSearchRequest;
import com.example.library_management.dto.response.BookResponse;
import com.example.library_management.entity.Book;
import com.example.library_management.exception.BookNotFoundException;
import com.example.library_management.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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

    public Book addBook(BookRequest request){

        Book book = new Book();
        book.setAuthor(request.getAuthor());
        book.setTitle(request.getTitle());
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


    public Page<Book> searchBooks(BookSearchRequest request){

        Sort sort = request.getDirection().equalsIgnoreCase("desc")
                ? Sort.by(request.getSortBy()).descending()
                : Sort.by(request.getSortBy()).ascending();

        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getSize(),
                sort);

        if(request.getAuthor() != null && request.getTitle() != null){
            return bookRepository.findByAuthorAndTitleContainingIgnoreCase(
                            request.getAuthor(),
                            request.getTitle(),
                            pageable);
        }

        if(request.getAuthor() != null) {
            return bookRepository.findByAuthor(
                    request.getAuthor(),
                    pageable);
        }

        if(request.getTitle() != null) {
            return bookRepository.findByTitleContainingIgnoreCase(
                    request.getTitle(),
                    pageable);
        }
        return bookRepository.findAll(pageable);
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
