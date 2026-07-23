package com.example.library_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/books")
    public String getBooks(){
        return "Welcome to Library Management System";
    }
}
