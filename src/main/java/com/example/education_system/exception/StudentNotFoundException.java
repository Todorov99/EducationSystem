package com.example.education_system.exception;

public class StudentNotFoundException  extends RuntimeException{
    public StudentNotFoundException(String message) {
        super(message);
    }
}
