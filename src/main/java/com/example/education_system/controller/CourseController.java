package com.example.education_system.controller;

import com.example.education_system.dto.CourseAllPropertiesDto;
import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.service.CourseService;
import com.example.education_system.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class CourseController {

    private  static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<Set<CourseAllPropertiesDto>> getAll() {
        logger.info("Getting all courses");
        return ResponseEntity.ok(courseService.getAllLogs());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseAllPropertiesDto> getAll(@PathVariable(name = "id")Integer id) {
        logger.info("Getting log by id");
        return ResponseEntity.ok(courseService.getOne(id));
    }
}
