package com.example.education_system.service.impl;

import com.example.education_system.domain.Student;
import com.example.education_system.repository.StudentRepository;
import com.example.education_system.service.StudentService;
import com.example.education_system.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static final String studentsYearOneFilePath = "./Course A_StudentsResults_Year 1.xlsx";
    private static final String studentsYearTwoFilePath = "./Course A_StudentsResults_Year 2.xlsx";

    private  static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final FileUtil fileUtil;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(FileUtil fileUtil, StudentRepository studentRepository) {
        this.fileUtil = fileUtil;
        this.studentRepository = studentRepository;
    }

    @Override
    public void seedStudents() throws IOException {
        logger.info("Seeding students...");

        List<Student> studentsYearOne = fileUtil.readXlsxFile(studentsYearOneFilePath);
        List<Student> studentsYearTwo = fileUtil.readXlsxFile(studentsYearTwoFilePath);

        studentsYearOne
                .forEach(studentRepository::saveAndFlush);
        studentsYearTwo
                .forEach(studentRepository::saveAndFlush);

        logger.info("Students successfully seeded");
    }
}
