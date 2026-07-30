package com.example.library_management.service;
import com.example.library_management.entity.Book;
import com.example.library_management.exception.BookNotFoundException;
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

    public Book addBook(Book book){
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    public Book getBookById(Long id){

        for(Book book : books){
            if(book.getId().equals(id)){
                return book;
            }
        }
        throw new BookNotFoundException("Book Not Found with id " + id);
    }

    public Book updateBook(Long id, Book updatedBook){

        for(Book book : books){
            if(book.getId().equals(id)){
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                return book;
            }
        }
        throw new BookNotFoundException("Book Not Found with id " + id);
    }

    public Book deleteBook(Long id){

        for(int i=0; i<books.size(); i++){
            if(books.get(i).getId().equals(id)) {
                Book deletedBook = books.get(i);
                books.remove(i);
                return deletedBook;
            }
        }

        throw new BookNotFoundException("Book Not Found With id " + id);
    }
}
