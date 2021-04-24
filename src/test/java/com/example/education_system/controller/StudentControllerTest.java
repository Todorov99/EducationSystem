package com.example.education_system.controller;

import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.dto.ResultsDto;
import com.example.education_system.dto.StudentAllPropertiesDto;
import com.example.education_system.service.impl.LogServiceImpl;
import com.example.education_system.service.impl.StudentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    private StudentController classUnderTest;

    @Mock
    private StudentServiceImpl studentService;

    @Before
    public void setUp(){

        classUnderTest=new StudentController(null,studentService);
    }

    @Test
    public void givenList_whenGetAllIsCalled_thenListIsReturend(){
        doReturn(new HashSet<>()).when(studentService).getAllStudents();

        classUnderTest.getAll();

        Mockito.verify(studentService).getAllStudents();
    }

    @Test
    public void givenStudent_whenGetOneIsCalled_thenCourseIsReturned(){
        doReturn(new StudentAllPropertiesDto()).when(studentService).getOne(1);

        classUnderTest.getAll(1);

        Mockito.verify(studentService).getOne(1);
    }

    @Test
    public void givenStudent_whenSeedIsCalled_thenCorrectResponseIsReturned() throws IOException {
        doNothing().when(studentService).seedStudents();

        classUnderTest.seedStudents();

        Mockito.verify(studentService).seedStudents();
    }

    @Test
    public void givenList_whenFindByComponentIsCalled_thenListIsReturend(){
        doReturn(new ArrayList<>()).when(studentService).getStudentsWithComponent("component");

        classUnderTest.getAll();

        Mockito.verify(studentService).getAllStudents();
    }

    @Test
    public void givenList_whenGetAverageIsCalled_thenAverageIsReturend(){
        doReturn(5.5).when(studentService).getAverageOfStudentsResults();

        classUnderTest.getAverageOfAllStudentsResults();

        Mockito.verify(studentService).getAverageOfStudentsResults();
    }

    @Test
    public void givenResultDto_whenGetSumarizedStudenInfo_thenInfoIsReturned(){
        ResultsDto testResultsDto = new ResultsDto();

        doReturn(new ArrayList<>()).when(studentService).getSummaryInfo(testResultsDto);

        classUnderTest.getSummarizedStudentInfo(testResultsDto);

        Mockito.verify(studentService).getSummaryInfo(testResultsDto);
    }


}
