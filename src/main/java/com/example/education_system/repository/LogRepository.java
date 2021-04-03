package com.example.education_system.repository;

import com.example.education_system.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

    List<Log> findAllByComponent(String component);

    @Query("select count(l) from Log as l where l.component = :component ")
    double getCountOfLogsByComponent(@Param(value = "component") String component);

    @Query("select count(l) from Log as l")
    double getCountOfLogs();
}
