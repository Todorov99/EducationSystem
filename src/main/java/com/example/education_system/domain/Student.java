package com.example.education_system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends BaseEntity {

    @Column(name = "result")
    private Integer result;

    public Student(Integer result) {
        this.result = result;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
