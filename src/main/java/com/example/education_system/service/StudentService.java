package com.example.education_system.service;

import com.example.education_system.dto.StudentDto;
import javassist.tools.rmi.ObjectNotFoundException;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    void seedStudents() throws IOException;

    List<StudentDto> getStudentsWithComponent(String component) throws ObjectNotFoundException;
}
