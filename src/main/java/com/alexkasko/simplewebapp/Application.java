package com.alexkasko.simplewebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.alexkasko")
//@SpringBootApplication(scanBasePackages = "com.alexkasko.simplewebapp")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}