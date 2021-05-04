package com.example.education_system.controller;

import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.dto.MaxAndMinStudentResultDto;
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

@RunWith(MockitoJUnitRunner.Silent.class)
public class StudentControllerTest {

    private StudentController classUnderTest;

    @Mock
    private StudentServiceImpl studentService;

    @Before
    public void setUp(){

        classUnderTest=new StudentController(studentService);
    }

    @Test
    public void givenList_whenGetAllIsCalled_thenListIsReturend(){
        doReturn(new HashSet<>()).when(studentService).getAll();

        classUnderTest.getAll();

        Mockito.verify(studentService).getAll();
    }

    @Test
    public void givenStudent_whenGetOneIsCalled_thenCourseIsReturned(){
        doReturn(new StudentAllPropertiesDto()).when(studentService).getStudentById(1);

        classUnderTest.getAllById(1);

        Mockito.verify(studentService).getStudentById(1);
    }

    @Test
    public void givenStudent_whenSeedIsCalled_thenCorrectResponseIsReturned() throws IOException {
        doNothing().when(studentService).seedStudents();

        classUnderTest.seedStudents();

        Mockito.verify(studentService).seedStudents();
    }

    @Test
    public void givenList_whenFindByComponentIsCalled_thenListIsReturend(){
        doReturn(new ArrayList<>()).when(studentService).getStudentByComponent("component");

        classUnderTest.getAll();

        Mockito.verify(studentService).getAll();
    }

    @Test
    public void givenComponent_whenGetAllStudents_thenListIsreturned() {
        doReturn(new ArrayList<>()).when(studentService).getStudentByComponent("testComponent");

        classUnderTest.getStudentsByComponent("testComponent");

        Mockito.verify(studentService).getStudentByComponent("testComponent");
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

    @Test
    public void givenComponentAndEventName_whenGetCentralTendetion_thenInfoIsReturned(){
        doReturn(new ArrayList<>()).when(studentService).getAbsoluteAndRelativeFrequencyOfStudentResult("testComponent", "testEventName");

        classUnderTest.getAbsoluteAndRelativeFrequency("testComponent", "testEventName");

        Mockito.verify(studentService).getAbsoluteAndRelativeFrequencyOfStudentResult("testComponent", "testEventName");
    }

    @Test
    public void givenComponent_whenGetScopeOfStudentResult_thenReturnScopeInfo() {
        doReturn(new MaxAndMinStudentResultDto()).when(studentService).getStudentsResultsScope("testComponent");

        classUnderTest.getScopeOfStudentResult("testComponent");
        Mockito.verify(studentService).getStudentsResultsScope("testComponent");
    }

    @Test
    public void givenEventName_whenGetDispersion_thenReturnDispersionInfo() {
        doReturn(3.5677575775).when(studentService).getDispersion("testEventName");

        classUnderTest.getDispersion("testEventName");
        Mockito.verify(studentService).getDispersion("testEventName");
    }

}
