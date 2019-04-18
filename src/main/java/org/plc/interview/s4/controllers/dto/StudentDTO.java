package org.plc.interview.s4.controllers.dto;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO {
    private Integer id;
    private String fistName;
    private String lastName;
    private List<Integer> courseIds;

    public StudentDTO() {
        this.courseIds = new ArrayList<>();
    }

    public StudentDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public StudentDTO setFistName(String fistName) {
        this.fistName = fistName;
        return this;
    }

    public String getFistName() {
        return fistName;
    }

    public StudentDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getId() {
        return id;
    }

    public List<Integer> getCourseIds() {
        return courseIds;
    }

    public void addCourse(int code) {
        courseIds.add(code);
    }
}
