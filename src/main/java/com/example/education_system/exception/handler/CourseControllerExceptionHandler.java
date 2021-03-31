package com.example.education_system.exception.handler;

import com.example.education_system.controller.CourseController;
import com.example.education_system.controller.LogController;
import com.example.education_system.dto.CustomError;
import com.example.education_system.exception.CourseNotFoundException;
import com.example.education_system.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = CourseController.class)
public class CourseControllerExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<CustomError> handleCourseNotFoundException(CourseNotFoundException e) {
        return new ResponseEntity<>(
                new CustomError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}
