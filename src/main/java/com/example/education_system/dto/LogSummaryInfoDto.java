package com.example.education_system.dto;

import java.io.Serializable;

public class LogSummaryInfoDto implements Serializable {

    private String timestamp;

    private String eventName;

    private String description;

    public LogSummaryInfoDto() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
