package com.example.education_system.controller;

import com.example.education_system.dto.CourseAllPropertiesDto;
import com.example.education_system.service.impl.CourseServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

    private CourseController classUnderTest;

    @Mock
    private CourseServiceImpl courseService;

    @Before
    public void setUp(){

        classUnderTest=new CourseController(courseService);
    }

    @Test
    public void givenList_whenGetAllIsCalled_thenListIsReturend(){

        doReturn(new HashSet<>()).when(courseService).getAll();

        classUnderTest.getAll();

        Mockito.verify(courseService).getAll();
    }

    @Test
    public void givenCourse_whenGetOneIsCalled_thenCourseIsReturned(){
        doReturn(new CourseAllPropertiesDto()).when(courseService).getCourseById(1);

        classUnderTest.getAllById(1);

        Mockito.verify(courseService).getCourseById(1);
    }





}
