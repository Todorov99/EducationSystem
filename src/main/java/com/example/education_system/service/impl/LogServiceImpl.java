package com.example.education_system.service.impl;

import com.example.education_system.domain.Log;
import com.example.education_system.domain.Student;
import com.example.education_system.repository.LogRepository;
import com.example.education_system.service.LogService;
import com.example.education_system.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final static String studentsActivitiesLogFilePath = "./Logs_Course A_StudentsActivities.xlsx";

    private  static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    private final FileUtil fileUtil;
    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(FileUtil fileUtil, LogRepository logRepository) {
        this.fileUtil = fileUtil;
        this.logRepository = logRepository;
    }

    @Override
    public void seedLogs() throws IOException {
        logger.info("Seeding logs...");

        List<Log> studentActivitiesLog = fileUtil.readXlsxFile(studentsActivitiesLogFilePath);

        studentActivitiesLog
                .forEach(logRepository::saveAndFlush);
   ;

        logger.info("Logs successfully seeded");
    }
}
