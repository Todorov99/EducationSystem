package com.example.education_system.service;

import com.example.education_system.dto.CourseAllPropertiesDto;
import com.example.education_system.dto.LogAllPropertiesDto;

import java.util.Set;

public interface CourseService {

    Set<CourseAllPropertiesDto> getAll();

    CourseAllPropertiesDto getCourseById(int id);
}
