package com.example.education_system.dto;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Set;

public class StudentWithoutRelationDto implements Serializable {

    @NonNull
    private Integer id;

    @NotNull
    private double result;

    private Integer year;


    public StudentWithoutRelationDto() {
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


}

