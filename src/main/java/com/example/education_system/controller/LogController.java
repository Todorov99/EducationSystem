package com.example.education_system.controller;

import com.example.education_system.domain.Log;
import com.example.education_system.domain.Student;
import com.example.education_system.service.LogService;
import com.example.education_system.service.StudentService;
import com.example.education_system.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class LogController {

    private final FileUtil fileUtil;
    private final LogService logService;

    @Autowired
    public LogController(FileUtil fileUtil, LogService logService) {
        this.fileUtil = fileUtil;
        this.logService = logService;
    }

    // TODO this will be remove.
    // Just for testing purposes for now.
    // This seeding should be in some of the services

    @GetMapping("/logs")
    public List<Log> showLogs() throws IOException {
        logService.seedLogs();

        return null;
    }
}
