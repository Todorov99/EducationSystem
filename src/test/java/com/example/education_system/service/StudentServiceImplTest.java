package com.example.education_system.service;

import com.example.education_system.domain.Course;
import com.example.education_system.domain.Log;
import com.example.education_system.domain.Student;
import com.example.education_system.dto.*;
import com.example.education_system.exception.ObjectNotFoundException;
import com.example.education_system.repository.CourseRepository;
import com.example.education_system.repository.StudentRepository;
import com.example.education_system.service.impl.CourseServiceImpl;
import com.example.education_system.service.impl.StudentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

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

    @Test
    public void givenComponentAndEventName_whenGetAbsoluteAndRelativeFrequencyOfStudentResult_thenReturnListOfCentralTendentionDto(){
        List<CentralTendentionDto> centralTendentionDtos = new ArrayList<>();
        centralTendentionDtos.add(new CentralTendentionDto(1, 2, 0.15));

        List<Student> testStudents = new ArrayList<>();
        testStudents.add(new Student(1, 2));

        doReturn(testStudents).when(studentRepository).getStudentsByLogComponentAndEventName(2.0, "testComponent", "testEventName");
        doReturn(100.0).when(studentRepository).countStudents();

        centralTendentionDtos = classUnderTest.getAbsoluteAndRelativeFrequencyOfStudentResult("testComponent", "testEventName");
        assertNotNull(centralTendentionDtos);
    }

    @Test
    public void givenResultDto_whenGetSummaryInfo_thenReturnListWithStudentSummarizedInfo() {

        Set<Log> testLogs  = new HashSet<>();
        testLogs.add(new Log("12/12/20", "testContext", "testComponent", "testEventName", "testDescription"));

        List<Student> listOfStudents  = new ArrayList<>();
        listOfStudents.add(new Student(1,5, testLogs));

        List<Double> testResults = new ArrayList<>();
        testResults.add(5.0);

        ResultsDto testResultsDto = new ResultsDto();
        testResultsDto.setEventName("Testing event name");
        testResultsDto.setResults(testResults);

        doReturn(listOfStudents).when(studentRepository).getStudentsByEventNameAndResult(testResultsDto.getEventName(), 5);


        List<StudentSummaryInfoDto> s = classUnderTest.getSummaryInfo(testResultsDto);
        assertNotNull(s);

    }

}
