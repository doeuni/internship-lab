package com.intern.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TokenprojApplication {

    public static void main(String[] args) {
                SpringApplication.run(TokenprojApplication.class, args);

    }
}
