package com.example.education_system.dto;

import java.io.Serializable;

public class LogAllPropertiesDto implements Serializable {

    private Integer id;

    private String timestamp;

    private String eventContext;

    private String component;

    private String eventName;

    private String description;

    private CourseWithoutRelationDto course;

    private StudentWithoutRelationDto student;

    public LogAllPropertiesDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public CourseWithoutRelationDto getCourse() {
        return course;
    }

    public void setCourse(CourseWithoutRelationDto course) {
        this.course = course;
    }

    public StudentWithoutRelationDto getStudent() {
        return student;
    }

    public void setStudent(StudentWithoutRelationDto student) {
        this.student = student;
    }
}
