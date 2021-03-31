package com.example.education_system.service;

import com.example.education_system.dto.CourseAllPropertiesDto;
import com.example.education_system.dto.LogAllPropertiesDto;

import java.util.Set;

public interface CourseService {

    Set<CourseAllPropertiesDto> getAllLogs();

    CourseAllPropertiesDto getOne(int id);
}
