package com.example.education_system.dto;

import com.example.education_system.domain.Log;
import com.example.education_system.domain.Student;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class CourseAllPropertiesDto {


    private Integer id;

    private List<StudentWithoutRelationDto> students;


    private Set<LogWithoutRelationDto> logs;

    public CourseAllPropertiesDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<StudentWithoutRelationDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentWithoutRelationDto> students) {
        this.students = students;
    }

    public Set<LogWithoutRelationDto> getLogs() {
        return logs;
    }

    public void setLogs(Set<LogWithoutRelationDto> logs) {
        this.logs = logs;
    }
}
