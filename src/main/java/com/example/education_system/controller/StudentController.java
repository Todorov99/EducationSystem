package com.example.education_system.controller;

import com.example.education_system.dto.*;
import com.example.education_system.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
public class StudentController {


    private  static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/students/summary")
    public ResponseEntity<List<StudentSummaryInfoDto>> getSummarizedStudentInfo(@RequestBody() ResultsDto resultsDto) {
        logger.info("Getting summarized Student info.");
        return ResponseEntity.ok(this.studentService.getSummaryInfo(resultsDto));
    }

    @PostMapping("/students/seed")
    public ResponseEntity<String> seedStudents() throws IOException {
        logger.info("Seeding students");
        studentService.seedStudents();
        return ResponseEntity.ok("Students successfully seeded");
    }

    @GetMapping("/students/findByComponent")
    public ResponseEntity<List<StudentWithoutRelationDto>> getAllStudent(@RequestParam(name = "component") String component)  {
        logger.info("Getting all students by component");
        return ResponseEntity.ok(this.studentService.getStudentsWithComponent(component));
    }

    @GetMapping("/students")
    public ResponseEntity<Set<StudentAllPropertiesDto>> getAll() {
        logger.info("Getting all students");
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentAllPropertiesDto> getAll(@PathVariable(name = "id")Integer id) {
        logger.info("Getting all students by id");
        return ResponseEntity.ok(studentService.getOne(id));
    }

    @GetMapping("/students/results/average")
    public ResponseEntity<String> getAverageOfAllStudentsResults() {
        logger.info("Getting average result of students");
        return ResponseEntity.ok(String.format("Students average of results: %f", this.studentService.getAverageOfStudentsResults()));
    }

    @GetMapping("/students/results/centralTendention")
    public ResponseEntity<List<AbosoluteAndRelativeFrequencyDto>> getAbsoluteAndRelativeFrequency(@RequestParam(name = "component") String component,
                                                                                                  @RequestParam(name = "eventName") String eventName) {
        logger.info("Getting central tendention");
        return ResponseEntity.ok(this.studentService.getAbsoluteAndRelativeFrequencyOfStudentResult(component, eventName));
    }

    @GetMapping("/students/results/scope")
    public ResponseEntity<MaxAndMinStudentResultDto> getScopeOfStudentResult(@RequestParam(name = "component") String component) {
        logger.info("Getting scope of student results");
        return ResponseEntity.ok(this.studentService.getStudentsResultsScope(component));
    }

    @GetMapping("/students/results/dispersion")
    public ResponseEntity<String> getDispersion(@RequestParam(name = "eventName") String eventName) {
        logger.info("Getting dispersion of student results");
        return ResponseEntity.ok(String.format("Dispersion of students results is %f", this.studentService.getDispersion(eventName)));
    }
}
