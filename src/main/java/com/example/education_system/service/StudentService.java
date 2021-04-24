package com.example.education_system.service;

import com.example.education_system.dto.*;
import javassist.tools.rmi.ObjectNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface StudentService {

    void seedStudents() throws IOException;

    List<StudentWithoutRelationDto> getStudentsWithComponent(String component);

    Set<StudentAllPropertiesDto> getAllStudents();

    StudentAllPropertiesDto getOne(int id);

    double getAverageOfStudentsResults();

    List<CentralTendentionDto> getAbsoluteAndRelativeFrequencyOfStudentResult(String component, String eventName);

    List<StudentSummaryInfoDto> getSummaryInfo(ResultsDto resultsDto);
}
