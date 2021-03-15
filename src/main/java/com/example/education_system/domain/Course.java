package com.example.education_system.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private Set<Log> logs;

    public Course(Integer id, List<Student> students, Set<Log> logs) {
        this.id = id;
        this.students = students;
        this.logs = logs;
    }

    public Course() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Set<Log> getCourses() {
        return logs;
    }

    public void setCourses(Set<Log> logs) {
        this.logs = logs;
    }
}
