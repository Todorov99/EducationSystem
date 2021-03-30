package com.example.education_system.dto;

import javax.persistence.Column;
import java.io.Serializable;

public class LogDto implements Serializable {

    private String timestamp;

    private String eventContext;

    private String component;

    private String eventName;

    private String description;

    public LogDto() {
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventContext() {
        return eventContext;
    }

    public void setEventContext(String eventContext) {
        this.eventContext = eventContext;
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
