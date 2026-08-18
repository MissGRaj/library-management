package com.example.library_management.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {

    public PasswordGenerator(PasswordEncoder passwordEncoder) {
        System.out.println("pass === "+passwordEncoder.encode("admin123"));
    }
}
