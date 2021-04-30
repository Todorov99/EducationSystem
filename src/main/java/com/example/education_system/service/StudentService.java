package com.example.education_system.service;

import com.example.education_system.dto.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface StudentService {

    void seedStudents() throws IOException;

    List<StudentWithoutRelationDto> getStudentsWithComponent(String component);

    Set<StudentAllPropertiesDto> getAllStudents();

    StudentAllPropertiesDto getOne(int id);

    double getAverageOfStudentsResults();

    List<AbosoluteAndRelativeFrequencyDto> getAbsoluteAndRelativeFrequencyOfStudentResult(String component, String eventName);

    List<StudentSummaryInfoDto> getSummaryInfo(ResultsDto resultsDto);

    MaxAndMinStudentResultDto getStudentsResultsScope(String component);

    double getDispersion(String eventName);
}
