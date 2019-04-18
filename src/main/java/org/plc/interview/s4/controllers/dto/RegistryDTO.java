package org.plc.interview.s4.controllers.dto;

public class RegistryDTO {
    private Long studentId;
    private Long courseId;

    public RegistryDTO() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
