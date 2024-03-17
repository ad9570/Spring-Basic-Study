package com.fastcampus.ch5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Ch5Application {
    public static void main(String[] args) {
        SpringApplication.run(Ch5Application.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello Spring Boot";
    }
}