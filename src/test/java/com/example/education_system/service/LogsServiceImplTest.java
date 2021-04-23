package com.example.education_system.service;

import com.example.education_system.domain.Course;
import com.example.education_system.domain.Log;
import com.example.education_system.dto.CourseAllPropertiesDto;
import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.repository.CourseRepository;
import com.example.education_system.repository.LogRepository;
import com.example.education_system.repository.StudentRepository;
import com.example.education_system.service.impl.LogServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class LogsServiceImplTest {

    private LogServiceImpl classUnderTest;

    @Mock
    private LogRepository logRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentRepository studentRepository;

    private ModelMapper modelMapper;

    @Before
    public void setUp(){
        modelMapper= new ModelMapper();
        classUnderTest=new LogServiceImpl(null,logRepository,studentRepository,courseRepository,modelMapper);
    }

    @Test
    public void givenListOfLogs_whenGetAllLogsIsCalled_thenListIsReturned(){

        List<Log> list  = new ArrayList<>();
        //String timestamp, String eventContext, String component, String eventName, String description
        list.add(new Log("timestamp","eventContent","component","eventName","description"));

        doReturn(list).when(logRepository).findAll();

        Set<LogAllPropertiesDto> expectedResults =  classUnderTest.getAllLogs();

        expectedResults.stream().map(s->modelMapper.map(s,Course.class));

        assertEquals(expectedResults.size(),list.size());
    }

    @Test
    public void givenCourse_whenGetOneIsCalled_thenCourseIsReturned(){
        Log log = new Log("timestamp","eventContent","component","eventName","description");

        doReturn(Optional.of(log)).when(logRepository).findById(1);

        LogAllPropertiesDto result =  classUnderTest.getOne(1);

        assertEquals(result,modelMapper.map(log,LogAllPropertiesDto.class));
    }
}
