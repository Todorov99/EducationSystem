package com.example.education_system.dto;

import java.io.Serializable;
import java.util.Set;

public class StudentSummaryInfoDto implements Serializable{

    private Integer id;
    private double result;
    private Set<LogSummaryInfoDto> logs;

    public StudentSummaryInfoDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Set<LogSummaryInfoDto> getLogs() {
        return logs;
    }

    public void setLogs(Set<LogSummaryInfoDto> logs) {
        this.logs = logs;
    }
}
