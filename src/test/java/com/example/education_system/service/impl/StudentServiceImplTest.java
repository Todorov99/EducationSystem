package com.example.education_system.service.impl;

import com.example.education_system.domain.Student;
import com.example.education_system.dto.StudentAllPropertiesDto;
import com.example.education_system.repository.StudentRepository;
import com.example.education_system.service.StudentService;
import com.example.education_system.util.impl.FileUtilImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentServiceImplTest {

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl studentService;

    private List<Student> expectedStudents =  new ArrayList<>();

    @BeforeEach()
    void setUp(){
        expectedStudents.add(new Student(7921, 4));
        expectedStudents.add(new Student(7922, 3.5));
    }

    @Test
    public void shouldGettAllStudents() {
        Mockito.when(this.studentRepository.findAll()).thenReturn(expectedStudents);
        Set<StudentAllPropertiesDto> s = this.studentService.getAllStudents();

        //List<Student> expectedStudents  = this.studentRepository.findAll();
        Assert.notNull(s);
    }
}