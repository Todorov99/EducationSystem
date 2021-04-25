package com.example.education_system.service.impl;

import com.example.education_system.domain.Student;
import com.example.education_system.dto.*;
import com.example.education_system.exception.LogNotFoundException;
import com.example.education_system.exception.ObjectNotFoundException;
import com.example.education_system.exception.StudentNotFoundException;
import com.example.education_system.repository.StudentRepository;
import com.example.education_system.service.StudentService;
import com.example.education_system.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final String studentsYearOneFilePath = "./Course A_StudentsResults_Year 1.xlsx";
    private static final String studentsYearTwoFilePath = "./Course A_StudentsResults_Year 2.xlsx";

    private  static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final FileUtil fileUtil;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(FileUtil fileUtil, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.fileUtil = fileUtil;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedStudents() throws IOException {
        logger.info("Seeding students...");

        List<Student> studentsYearOne = fileUtil.readXlsxFile(studentsYearOneFilePath);
        List<Student> studentsYearTwo = fileUtil.readXlsxFile(studentsYearTwoFilePath);

        studentsYearOne.addAll(studentsYearTwo);

        studentsYearOne.forEach(studentRepository::saveAndFlush);

        logger.info("Students successfully seeded");
    }

    @Override
    public List<StudentWithoutRelationDto> getStudentsWithComponent(String component) {
        logger.info("Getting stydent by component name" + component);

        List<StudentWithoutRelationDto> students = new ArrayList<>();

        List<Student> studentsWithComponent = this.studentRepository.getStudentsByComponentName(component);

        for (Student student : studentsWithComponent) {
            StudentWithoutRelationDto studentWithoutRelationDto = this.modelMapper.map(student, StudentWithoutRelationDto.class);
            students.add(studentWithoutRelationDto);
        }

        if (students.size() == 0) {
            throw new com.example.education_system.exception.ObjectNotFoundException("Student with componenet " + component + " not found");
        }

        return students;
    }

    @Override
    public Set<StudentAllPropertiesDto> getAllStudents() {
        logger.info("Getting all students");

        return studentRepository
                .findAll()
                .stream()
                .map(log-> modelMapper.map(log, StudentAllPropertiesDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public StudentAllPropertiesDto getOne(int id) {
        logger.info("Getting student by id: " + id);

        return modelMapper.map(studentRepository.findById(id).orElseThrow(() -> {
            throw new StudentNotFoundException("cannot find log with id "+ id);
        }), StudentAllPropertiesDto.class);
    }

    @Override
    public double getAverageOfStudentsResults() {
        return this.studentRepository.getAverageOfAllResult();
    }

    @Override
    public List<CentralTendentionDto> getAbsoluteAndRelativeFrequencyOfStudentResult(String component, String eventName) {
        logger.info("Getting central tendention");

        List<CentralTendentionDto> centralTendention = new ArrayList<>();

        for (int i =2; i <= 6; i++) {
            List<Student> studentsWithConcreteResults = this.studentRepository.getStudentsByLogComponentAndEventName((double)i, component, eventName);

            int absoluteFrequency = studentsWithConcreteResults.size();
            double relativeFrequnecy = studentsWithConcreteResults.size() / this.studentRepository.countStudents();
            centralTendention.add(new CentralTendentionDto(i, absoluteFrequency, relativeFrequnecy));

        }

        return centralTendention;
    }

    @Override
    public List<StudentSummaryInfoDto> getSummaryInfo(ResultsDto resultsDto) {
        List<StudentSummaryInfoDto> studentSummaryInfo = new ArrayList<>();

        resultsDto.getResults()
                .forEach(
                        studentResult -> {
                            this.studentRepository.getStudentsByEventNameAndResult(resultsDto.getEventName(), studentResult)
                                    .forEach(
                                            student -> {
                                                studentSummaryInfo.add(
                                                        this.modelMapper.map(student, StudentSummaryInfoDto.class)
                                                );
                                            }
                                    );
                        }
                );


        if (studentSummaryInfo.size() == 0) {
            throw new ObjectNotFoundException("There is not such information found");
        }

        return studentSummaryInfo;
    }

}
