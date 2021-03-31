package com.example.education_system.exception.handler;

import com.example.education_system.controller.LogController;
import com.example.education_system.dto.CustomError;
import com.example.education_system.exception.LogNotFoundException;
import com.example.education_system.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = LogController.class)
public class LogControllerExceptionHandler {

    @ExceptionHandler(LogNotFoundException.class)
    public ResponseEntity<CustomError> handleLogNotFound(LogNotFoundException e) {
        return new ResponseEntity<>(
                new CustomError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}
