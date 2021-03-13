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

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private StudentService studentService;

    // TODO this will be remove.
    // Just for testing purposes for now.
    @GetMapping("/")
    public List<Student> showStudents() throws IOException {
        studentService.seedStudents();

        List<Student> students = fileUtil.readXlsxFile("./Course A_StudentsResults_Year 1.xlsx");

        return students;
    }
}
