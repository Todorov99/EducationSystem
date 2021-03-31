package com.example.education_system.dto;

public class CourseWithoutRelationDto {

    private Integer id;

    public CourseWithoutRelationDto(Integer id) {
        this.id = id;
    }

    public CourseWithoutRelationDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
