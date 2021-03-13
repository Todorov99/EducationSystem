package com.example.education_system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends BaseEntity{

    @Column(name = "result")
    private double result;

    @Column(name = "year")
    private Integer year;

    public Student() {
    }

    public Student(Integer id, double result) {
        super(id);
        this.result = result;
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
