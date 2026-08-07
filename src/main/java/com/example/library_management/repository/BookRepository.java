package com.example.library_management.repository;

import com.example.library_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorAndTitleContainingIgnoreCase(String author, String title);
}
