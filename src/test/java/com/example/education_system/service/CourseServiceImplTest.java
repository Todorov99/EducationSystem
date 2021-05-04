package com.example.education_system.service;

import com.example.education_system.domain.Course;
import com.example.education_system.dto.CourseAllPropertiesDto;
import com.example.education_system.repository.CourseRepository;
import com.example.education_system.service.impl.CourseServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceImplTest {

    private CourseServiceImpl classUnderTest;

    @Mock
    private CourseRepository courseRepository;

    private ModelMapper modelMapper;

    @Before
    public void setUp(){
        modelMapper= new ModelMapper();
        classUnderTest=new CourseServiceImpl(courseRepository,modelMapper);
    }

    @Test
    public void givenListOfCourses_whenGetAllLogsIsCalled_thenListIsReturned(){

        List<Course> list = new ArrayList<>();
        Course course1 = new Course();
        course1.setId(1);
        Course course2 = new Course();
        course2.setId(2);
        list.add(course1);
        list.add(course2);

        doReturn(list).when(courseRepository).findAll();

        Set<CourseAllPropertiesDto> expectedResults =  classUnderTest.getAll();

        expectedResults.stream().map(s->modelMapper.map(s,Course.class));

        assertEquals(expectedResults.size(),list.size());
    }

    @Test
    public void givenCourse_whenGetOneIsCalled_thenCourseIsReturned(){
        Course course = new Course();
        course.setId(1);
        course.setStudents(new ArrayList<>());
        course.setLogs(new HashSet<>());

        doReturn(Optional.of(course)).when(courseRepository).findById(1);

       CourseAllPropertiesDto result =  classUnderTest.getCourseById(1);

       assertEquals(result,modelMapper.map(course,CourseAllPropertiesDto.class));
    }
}
