package com.example.education_system.controller;

import com.example.education_system.domain.Student;
import com.example.education_system.dto.StudentDto;
import com.example.education_system.repository.StudentRepository;
import com.example.education_system.service.StudentService;
import com.example.education_system.util.FileUtil;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

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


    @PostMapping("/seedStudents")
    public ResponseEntity<String> showStudents() throws IOException {
        studentService.seedStudents();
        return new ResponseEntity<>("Students successfully seeded", HttpStatus.OK);
    }

    @GetMapping("/findByComponent")
    public ResponseEntity<List<StudentDto>> getAllStudent(@RequestParam(name = "component") String component) throws ObjectNotFoundException {
        return new ResponseEntity<>(this.studentService.getStudentsWithComponent(component), HttpStatus.OK);
    }

}
