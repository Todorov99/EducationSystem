package com.example.education_system.exception;

public class LogNotFoundException extends RuntimeException{

    public LogNotFoundException(String message) {
        super(message);
    }
}
