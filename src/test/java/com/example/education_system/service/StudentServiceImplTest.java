package com.example.education_system.service;

import com.example.education_system.domain.Log;
import com.example.education_system.domain.Student;
import com.example.education_system.dto.*;
import com.example.education_system.exception.NoStudentsForStandardDeviationException;
import com.example.education_system.exception.ObjectNotFoundException;
import com.example.education_system.repository.StudentRepository;
import com.example.education_system.service.impl.StudentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.Silent.class)
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
        list.add(new Student(1,5));

        doReturn(list).when(studentRepository).findAll();

        Set<StudentAllPropertiesDto> expectedResults =  classUnderTest.getAll();

        expectedResults.stream().map(s->modelMapper.map(s, Student.class));

        assertEquals(expectedResults.size(),list.size());
    }

    @Test
    public void givenStudent_whenGetOneIsCalled_thenCourseIsReturned(){
        Student student  = new Student(1,5);

        doReturn(Optional.of(student)).when(studentRepository).findById(1);

        StudentAllPropertiesDto result =  classUnderTest.getStudentById(1);

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
        List<AbosoluteAndRelativeFrequencyDto> abosoluteAndRelativeFrequencyDtos = new ArrayList<>();
        abosoluteAndRelativeFrequencyDtos.add(new AbosoluteAndRelativeFrequencyDto(1, 2, 0.15));

        List<Student> testStudents = new ArrayList<>();
        testStudents.add(new Student(1, 2));

        doReturn(testStudents).when(studentRepository).getStudentsByLogComponentAndEventName(2.0, "testComponent", "testEventName");
        doReturn(100.0).when(studentRepository).countStudents();

        abosoluteAndRelativeFrequencyDtos = classUnderTest.getAbsoluteAndRelativeFrequencyOfStudentResult("testComponent", "testEventName");
        assertTrue(abosoluteAndRelativeFrequencyDtos.size() != 0);
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
        assertTrue(s.size() != 0);

    }

    @Test
    public void givenResultDtoWithInvalidInfo_whenGetSummaryInfo_thenReturnError(){

        List<Double> testResults = new ArrayList<>();
        testResults.add(5.0);

        ResultsDto testResultsDto = new ResultsDto();
        testResultsDto.setEventName("Testing event name");
        testResultsDto.setResults(testResults);

        doReturn(new ArrayList<>()).when(studentRepository).getStudentsByEventNameAndResult("invalid", 5);


        assertThrows(ObjectNotFoundException.class, () -> {
           classUnderTest.getSummaryInfo(testResultsDto);
        });

    }

    @Test
    public void givenValidComponent_whenGetStudentsResultsScope_thenReturnMaxAndMinStudentResultDto() {
        List<Student> listOfStudents  = new ArrayList<>();
        listOfStudents.add(new Student(1,5));
        listOfStudents.add(new Student(1,2));
        listOfStudents.add(new Student(1,3));

        doReturn(listOfStudents).when(studentRepository).getStudentsByComponentName("testComponent");

        MaxAndMinStudentResultDto actualMaxAndMinStudentResultDto = classUnderTest.getStudentsResultsScope("testComponent");
        assertEquals("testComponent", actualMaxAndMinStudentResultDto.getComponent());
        assertEquals(2.0, actualMaxAndMinStudentResultDto.getMinResult(), 0.1);
        assertEquals(5.0, actualMaxAndMinStudentResultDto.getMaxResult(), 0.1);
        assertEquals(3.0, actualMaxAndMinStudentResultDto.getScope(), 0.1);
    }

    @Test
    public void givenInvalidComponent_whenGetStudentsResultsScope_thenReturnError() {
        List<Student> listOfStudents  = new ArrayList<>();
        doReturn(listOfStudents).when(studentRepository).getStudentsByComponentName("invalidComponent");

        assertThrows(ObjectNotFoundException.class, () -> {
            classUnderTest.getStudentsResultsScope("invalidComponent");
        });
    }

    @Test
    public void givenValidEventName_whenGetDispersion_thenReturnResultForDispersion() {
        List<Student> listOfStudents  = new ArrayList<>();
        listOfStudents.add(new Student(1,5));
        listOfStudents.add(new Student(1,2));
        listOfStudents.add(new Student(1,3));

        doReturn(listOfStudents).when(studentRepository).getAllStudentsByEventName("testEventName");

        double actualResult = classUnderTest.getDispersion("testEventName");
        assertEquals(3.1622776601683795, actualResult, 0.1);
    }

    @Test
    public void givenListOfStudent_whenGetStandardDeviation_thenReturnCorrectDeviation(){
        List<Student> listOfStudents  = new ArrayList<>();
        listOfStudents.add(new Student(1,5));
        listOfStudents.add(new Student(1,2));
        listOfStudents.add(new Student(1,3));

        doReturn(listOfStudents).when(studentRepository).findAll();

        double result = classUnderTest.getStandardDeviation();


        assertEquals(1.247219128924647, result,0.001);
    }

    @Test(expected = NoStudentsForStandardDeviationException.class)
    public void givenEmptyListOfStudent_whenGetStandardDeviation_thenReturnCorrectDeviation(){
        List<Student> listOfStudents  = new ArrayList<>();

        doReturn(listOfStudents).when(studentRepository).findAll();

        classUnderTest.getStandardDeviation();
    }


//    @Override
//    public double getStandardDeviation() {
//
////        Стъпка 1: Намери средната стойност.
////        Стъпка 2: За всяка стойност, намери квадрата от разликата между конкретната стойност от набора данни и средната стойност.
////        Стъпка 3: Събери стойностите от стъпка 2.
////        Стъпка 4: Раздели на броя на стойностите от набора данни.
////        Стъпка 5: Изчисли корен квадратен от получената сума.
//
//        List<Student> students = studentRepository.findAll();
//
//        if(students.size()==0){
//            throw new NoStudentsForStandardDeviationException("Cannot calculate standard deviation");
//        }
//
//        double average  = students.stream().mapToDouble(Student::getResult).average().orElse(0.0);
//
//        double sum = 0.0;
//
//        for (Student s: students) {
//            double step2 = s.getResult() - average;
//            double result = Math.pow(step2,2);
//            sum+=result;
//        }
//        sum = sum/students.size();
//
//        return Math.


    @Test
    public void givenInvalidEventName_whenGetDispersion_thenReturnError() {
        List<Student> listOfStudents  = new ArrayList<>();

        doReturn(listOfStudents).when(studentRepository).getAllStudentsByEventName("invalidEventName");

        assertThrows(ObjectNotFoundException.class, () -> {
            classUnderTest.getDispersion("invalidEventName");
        });
    }
}
