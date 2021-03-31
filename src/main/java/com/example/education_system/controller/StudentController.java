package com.example.education_system.controller;

import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.dto.StudentAllPropertiesDto;
import com.example.education_system.dto.StudentWithoutRelationDto;
import com.example.education_system.service.StudentService;
import com.example.education_system.util.FileUtil;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/students/findByComponent")
    public ResponseEntity<List<StudentWithoutRelationDto>> getAllStudent(@RequestParam(name = "component") String component) throws ObjectNotFoundException {
        return new ResponseEntity<>(this.studentService.getStudentsWithComponent(component), HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<Set<StudentAllPropertiesDto>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentAllPropertiesDto> getAll(@PathVariable(name = "id")Integer id) {
        return ResponseEntity.ok(studentService.getOne(id));
    }

}
