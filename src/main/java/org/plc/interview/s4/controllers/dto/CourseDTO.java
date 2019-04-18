package org.plc.interview.s4.controllers.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private Integer code;
    private String title;
    private String description;
    private List<Integer> studentIds;

    public CourseDTO() {
        this.studentIds = new ArrayList<>();
    }

    public CourseDTO setCode(Integer code) {
        this.code = code;
        return this;
    }

    public CourseDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CourseDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }

    public List<Integer> getStudentIds() {
        return studentIds;
    }

    public void addStudent(int code) {
        studentIds.add(code);
    }
}
