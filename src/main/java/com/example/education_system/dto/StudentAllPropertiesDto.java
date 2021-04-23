package com.example.education_system.dto;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class StudentAllPropertiesDto {

    @NonNull
    private Integer id;

    @NotNull
    private double result;

    private Integer year;

    private List<CourseWithoutRelationDto> courses;

    private Set<LogWithoutRelationDto> logs;

    public StudentAllPropertiesDto() {
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<CourseWithoutRelationDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseWithoutRelationDto> courses) {
        this.courses = courses;
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
        StudentAllPropertiesDto that = (StudentAllPropertiesDto) o;
        return Double.compare(that.result, result) == 0 &&
                id.equals(that.id) &&
                Objects.equals(year, that.year) &&
                Objects.equals(courses, that.courses) &&
                Objects.equals(logs, that.logs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, result, year, courses, logs);
    }
}
