package com.example.education_system.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class ResultsDto implements Serializable {

    public String eventName;
    public List<Double> results;

    public ResultsDto() {
    }

    public List<Double> getResults() {
        return results;
    }

    public void setResults(List<Double> results) {
        this.results = results;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
