package com.example.education_system.dto;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogAllPropertiesDto that = (LogAllPropertiesDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(eventContext, that.eventContext) &&
                Objects.equals(component, that.component) &&
                Objects.equals(eventName, that.eventName) &&
                Objects.equals(description, that.description) &&
                Objects.equals(course, that.course) &&
                Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, eventContext, component, eventName, description, course, student);
    }
}
