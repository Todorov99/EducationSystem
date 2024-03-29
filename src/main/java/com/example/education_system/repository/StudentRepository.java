package com.example.education_system.repository;

import com.example.education_system.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select s from Student as s join s.logs as sl where sl.component = :component")
   List<Student> getStudentsByComponentName(@Param(value = "component") String component);

    @Query("select avg(s.result) from Student as s")
    double getAverageOfAllResult();

    @Query("select s from Student as s join s.logs as sl where s.result = :result and sl.component = :component and sl.eventName = :eventName")
    List<Student> getStudentsByLogComponentAndEventName(@Param(value = "result") double result, @Param(value = "component") String component,
                                                        @Param(value = "eventName") String eventName);

    @Query("select count(s) from Student as s")
    double countStudents();

    @Query("select s from Student as s join s.logs as sl where sl.eventName = :eventName")
    List<Student> getAllStudentsByEventName(@Param(value = "eventName")String eventName);

    @Query("select distinct s from Student as s join s.logs as sl where sl.eventName = :eventName and s.result = :result")
    List<Student> getStudentsByEventNameAndResult(@Param(value = "eventName") String eventName, @Param(value = "result") double result);
}
