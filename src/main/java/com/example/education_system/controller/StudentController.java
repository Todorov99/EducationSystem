package com.example.education_system.controller;

import com.example.education_system.dto.*;
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


    @GetMapping("/students/summary")
    public ResponseEntity<List<StudentSummaryInfoDto>> getSummarizedStudentInfo(@RequestBody() ResultsDto resultsDto) {
        return ResponseEntity.ok(this.studentService.getSummaryInfo(resultsDto));
    }

    @PostMapping("/students/seed")
    public ResponseEntity<String> seedStudents() throws IOException {
        studentService.seedStudents();
        return ResponseEntity.ok("Students successfully seeded");
    }

    @GetMapping("/students/findByComponent")
    public ResponseEntity<List<StudentWithoutRelationDto>> getAllStudent(@RequestParam(name = "component") String component)  {
        return ResponseEntity.ok(this.studentService.getStudentsWithComponent(component));
    }

    @GetMapping("/students")
    public ResponseEntity<Set<StudentAllPropertiesDto>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentAllPropertiesDto> getAll(@PathVariable(name = "id")Integer id) {
        return ResponseEntity.ok(studentService.getOne(id));
    }

    @GetMapping("/students/results/average")
    public ResponseEntity<String> getAverageOfAllStudentsResults() {
        return ResponseEntity.ok(String.format("Students average of results: %f", this.studentService.getAverageOfStudentsResults()));
    }

    @GetMapping("/students/results/centralTendention")
    public ResponseEntity<List<CentralTendentionDto>> getCentralTendention(@RequestParam(name = "component") String component,
                                                                           @RequestParam(name = "eventName") String eventName) {
        return ResponseEntity.ok(this.studentService.getAbsoluteAndRelativeFrequencyOfStudentResult(component, eventName));
    }
}
