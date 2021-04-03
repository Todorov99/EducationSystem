package com.example.education_system.exception.handler;


import com.example.education_system.controller.LogController;
import com.example.education_system.controller.StudentController;
import com.example.education_system.dto.CustomError;
import com.example.education_system.exception.LogNotFoundException;
import com.example.education_system.exception.ObjectNotFoundException;
import com.example.education_system.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = StudentController.class)
public class StudentControllerExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<CustomError> handleStudentNotFoundException(StudentNotFoundException e) {
        return new ResponseEntity<>(
                new CustomError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<CustomError> handleObjectNotFoundException(ObjectNotFoundException e) {
        return new ResponseEntity<>(
                new CustomError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}
