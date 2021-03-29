package com.example.education_system.controller;

import com.example.education_system.domain.Student;
import com.example.education_system.service.StudentService;
import com.example.education_system.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class StudentController {

    private final FileUtil fileUtil;
    private final StudentService studentService;

    @Autowired
    public StudentController(FileUtil fileUtil, StudentService studentService) {
        this.fileUtil = fileUtil;
        this.studentService = studentService;
    }

    // TODO this will be remove.
    // Just for testing purposes for now.
    // This seeding should be in some of the services

    @GetMapping("/students")
    public List<Student> showStudents() throws IOException {
        studentService.seedStudents();

        return null;
    }
}
