package com.example.education_system.dto;

import java.io.Serializable;

public class MaxAndMinStudentResultDto implements Serializable {

    private String component;
    private double minResult;
    private double maxResult;
    private double scope;

    public MaxAndMinStudentResultDto() {
    }

    public MaxAndMinStudentResultDto(String component, double minResult, double maxResult, double scope) {
        this.component = component;
        this.minResult = minResult;
        this.maxResult = maxResult;
        this.scope = scope;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public double getMinResult() {
        return minResult;
    }

    public void setMinResult(double minResult) {
        this.minResult = minResult;
    }

    public double getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(double maxResult) {
        this.maxResult = maxResult;
    }

    public double getScope() {
        return scope;
    }

    public void setScope(double scope) {
        this.scope = scope;
    }
}
