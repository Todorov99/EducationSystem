package com.example.education_system.dto;

public class CentralTendentionDto {

    int result;

    int absoluteFrequency;

    double relativeFrequency;

    public CentralTendentionDto() {
    }

    public CentralTendentionDto(int result, int absoluteFrequency, double relativeFrequency) {
        this.result = result;
        this.absoluteFrequency = absoluteFrequency;
        this.relativeFrequency = relativeFrequency;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getAbsoluteFrequency() {
        return absoluteFrequency;
    }

    public void setAbsoluteFrequency(int absoluteFrequency) {
        this.absoluteFrequency = absoluteFrequency;
    }

    public double getRelativeFrequency() {
        return relativeFrequency;
    }

    public void setRelativeFrequency(double relativeFrequency) {
        this.relativeFrequency = relativeFrequency;
    }
}
