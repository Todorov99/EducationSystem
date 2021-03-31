package com.example.education_system.service.impl;

import com.example.education_system.dto.CourseAllPropertiesDto;
import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.exception.CourseNotFoundException;
import com.example.education_system.exception.LogNotFoundException;
import com.example.education_system.repository.CourseRepository;
import com.example.education_system.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<CourseAllPropertiesDto> getAllLogs() {
        return courseRepository
                .findAll()
                .stream()
                .map(log -> modelMapper.map(log, CourseAllPropertiesDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public CourseAllPropertiesDto getOne(int id) {

        return modelMapper.map(courseRepository.findById(id).orElseThrow(() -> {
            throw new CourseNotFoundException("cannot find log with id " + id);
        }), CourseAllPropertiesDto.class);


    }
}
