package com.example.education_system.service;

import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.dto.StudentAllPropertiesDto;
import com.example.education_system.dto.StudentWithoutRelationDto;
import javassist.tools.rmi.ObjectNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface StudentService {

    void seedStudents() throws IOException;

    List<StudentWithoutRelationDto> getStudentsWithComponent(String component);

    Set<StudentAllPropertiesDto> getAllStudents();

    StudentAllPropertiesDto getOne(int id);
}
