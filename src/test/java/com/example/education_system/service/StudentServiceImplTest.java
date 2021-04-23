package com.example.education_system.service;

import com.example.education_system.domain.Course;
import com.example.education_system.domain.Log;
import com.example.education_system.domain.Student;
import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.dto.StudentAllPropertiesDto;
import com.example.education_system.repository.CourseRepository;
import com.example.education_system.repository.StudentRepository;
import com.example.education_system.service.impl.CourseServiceImpl;
import com.example.education_system.service.impl.StudentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {
    private StudentServiceImpl classUnderTest;

    @Mock
    private StudentRepository studentRepository;

    private ModelMapper modelMapper;

    @Before
    public void setUp(){
        modelMapper= new ModelMapper();
        classUnderTest=new StudentServiceImpl(null,studentRepository,modelMapper);
    }

    @Test
    public void givenListOfStudents_whenGetAllLogsIsCalled_thenListIsReturned(){

        List<Student> list  = new ArrayList<>();
        //String timestamp, String eventContext, String component, String eventName, String description
        list.add(new Student(1,5));

        doReturn(list).when(studentRepository).findAll();

        Set<StudentAllPropertiesDto> expectedResults =  classUnderTest.getAllStudents();

        expectedResults.stream().map(s->modelMapper.map(s, Student.class));

        assertEquals(expectedResults.size(),list.size());
    }

    @Test
    public void givenStudent_whenGetOneIsCalled_thenCourseIsReturned(){
        Student student  = new Student(1,5);

        doReturn(Optional.of(student)).when(studentRepository).findById(1);

        StudentAllPropertiesDto result =  classUnderTest.getOne(1);

        assertEquals(result,modelMapper.map(student,StudentAllPropertiesDto.class));
    }


    @Test
    public void givenStudents_whenGetAverageResultISCalled_correctResponseIsReturned(){
        doReturn(5.5).when(studentRepository).getAverageOfAllResult();

        double result = classUnderTest.getAverageOfStudentsResults();

        assertEquals(5.5,result,0.1);
    }
}
