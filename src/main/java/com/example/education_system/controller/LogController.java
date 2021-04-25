package com.example.education_system.controller;

import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
public class LogController {

    private  static final Logger logger = LoggerFactory.getLogger(LogController.class);
    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }


    @PostMapping("/logs/seed")
    public ResponseEntity<String> showLogs() throws IOException {
        logger.info("Seeding logs");
        logService.seedLogs();
        return new ResponseEntity<>("Logs successfully seeded", HttpStatus.OK);
    }

    @GetMapping("/logs")
    public ResponseEntity<Set<LogAllPropertiesDto>> getAll() {
        logger.info("Getting all logs");
        return ResponseEntity.ok(logService.getAllLogs());
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<LogAllPropertiesDto> getAll(@PathVariable(name = "id")Integer id) {
        logger.info("Getting log by id");
        return ResponseEntity.ok(logService.getOne(id));
    }
}
