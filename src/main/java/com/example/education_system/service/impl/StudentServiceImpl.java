package com.example.education_system.service.impl;

import com.example.education_system.domain.Student;
import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.dto.StudentAllPropertiesDto;
import com.example.education_system.dto.StudentWithoutRelationDto;
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
    public List<StudentWithoutRelationDto> getStudentsWithComponent(String component) throws ObjectNotFoundException {
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
        return studentRepository
                .findAll()
                .stream()
                .map(log-> modelMapper.map(log, StudentAllPropertiesDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public StudentAllPropertiesDto getOne(int id) {
        return modelMapper.map(studentRepository.findById(id).orElseThrow(() -> {
            throw new StudentNotFoundException("cannot find log with id "+ id);
        }), StudentAllPropertiesDto.class);
    }
}
