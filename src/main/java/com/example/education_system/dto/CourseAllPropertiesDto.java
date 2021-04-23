package com.example.education_system.dto;

import com.example.education_system.domain.Log;
import com.example.education_system.domain.Student;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseAllPropertiesDto that = (CourseAllPropertiesDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(students, that.students) &&
                Objects.equals(logs, that.logs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, students, logs);
    }
}
