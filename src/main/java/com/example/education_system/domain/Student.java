package com.example.education_system.domain;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id")
    @NonNull
    private Integer id;

    @Column(name = "result")
    @NotNull
    private double result;

    @Column(name = "year")
    private Integer year;

    @ManyToMany(mappedBy = "students",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Course> courses;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Log> logs;

    public Student() {
    }

    public Student(Integer id, double result) {
        this.id = id;
        this.result = result;
    }

    public Student(Integer id, double result, Set<Log> logs) {
        this.id = id;
        this.result = result;
        this.logs = logs;
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

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }
}
