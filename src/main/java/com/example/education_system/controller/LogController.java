package com.example.education_system.controller;

import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;

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


    @GetMapping("/logs")
    public ResponseEntity<Set<LogAllPropertiesDto>> getAll() {
        return ResponseEntity.ok(logService.getAllLogs());
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<LogAllPropertiesDto> getAll(@PathVariable(name = "id")Integer id) {
        return ResponseEntity.ok(logService.getOne(id));
    }
}
