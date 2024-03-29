package com.example.education_system.repository;

import com.example.education_system.domain.Course;
import com.example.education_system.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
