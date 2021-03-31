package com.example.education_system.repository;

import com.example.education_system.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select s from Student as s join s.logs as sl where sl.component = :component")
    List<Student> getStudentsByComponentName(@Param(value = "component") String component);
}
