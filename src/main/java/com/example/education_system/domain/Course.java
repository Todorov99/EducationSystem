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


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
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

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }
}
