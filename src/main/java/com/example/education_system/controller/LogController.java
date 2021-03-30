package com.example.education_system.controller;

import com.example.education_system.domain.Log;
import com.example.education_system.domain.Student;
import com.example.education_system.service.LogService;
import com.example.education_system.service.StudentService;
import com.example.education_system.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }


    @PostMapping("/seedLogs")
    public ResponseEntity<String> showLogs() throws IOException {
        logService.seedLogs();
        return new ResponseEntity<>("Students successfully seeded", HttpStatus.OK);
    }
}
