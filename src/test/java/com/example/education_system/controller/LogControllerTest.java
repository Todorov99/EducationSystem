package com.example.education_system.controller;

import com.example.education_system.domain.Log;
import com.example.education_system.dto.CourseAllPropertiesDto;
import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.service.impl.CourseServiceImpl;
import com.example.education_system.service.impl.LogServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class LogControllerTest {

    private LogController classUnderTest;

    @Mock
    private LogServiceImpl logService;

    @Before
    public void setUp(){

        classUnderTest=new LogController(logService);
    }

    @Test
    public void givenList_whenGetAllIsCalled_thenListIsReturend(){

        doReturn(new HashSet<>()).when(logService).getAll();

        classUnderTest.getAll();

        Mockito.verify(logService).getAll();
    }

    @Test
    public void givenCourse_whenGetOneIsCalled_thenCourseIsReturned(){
        doReturn(new LogAllPropertiesDto()).when(logService).getLogById(1);

        classUnderTest.getAll(1);

        Mockito.verify(logService).getLogById(1);
    }
}
