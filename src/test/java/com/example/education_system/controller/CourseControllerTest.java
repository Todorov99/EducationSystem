package com.example.education_system.controller;

import com.example.education_system.domain.Course;
import com.example.education_system.dto.CentralTendentionDto;
import com.example.education_system.dto.CourseAllPropertiesDto;
import com.example.education_system.dto.StudentAllPropertiesDto;
import com.example.education_system.dto.StudentWithoutRelationDto;
import com.example.education_system.repository.CourseRepository;
import com.example.education_system.service.impl.CourseServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
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

        doReturn(new HashSet<>()).when(courseService).getAllLogs();

        classUnderTest.getAll();

        Mockito.verify(courseService).getAllLogs();
    }

    @Test
    public void givenCourse_whenGetOneIsCalled_thenCourseIsReturned(){
        doReturn(new CourseAllPropertiesDto()).when(courseService).getOne(1);

        classUnderTest.getAll(1);

        Mockito.verify(courseService).getOne(1);
    }





}
