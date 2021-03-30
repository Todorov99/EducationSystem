package com.example.education_system.dto;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Set;

public class StudentDto implements Serializable {

    @NonNull
    private Integer id;

    @NotNull
    private double result;

    private Integer year;

    private Set<LogDto> logs;

    public StudentDto() {
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

    public Set<LogDto> getLogs() {
        return logs;
    }

    public void setLogs(Set<LogDto> logs) {
        this.logs = logs;
    }
}
