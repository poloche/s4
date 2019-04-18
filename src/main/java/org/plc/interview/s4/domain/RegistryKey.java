package org.plc.interview.s4.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RegistryKey implements Serializable {
    @Column(name = "student_id")
    private
    Long studentId;

    @Column(name = "class_id")
    private
    Long courseId;

    public RegistryKey() {
    }

    public RegistryKey(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
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
