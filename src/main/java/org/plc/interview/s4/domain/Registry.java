package org.plc.interview.s4.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Registry {
    @EmbeddedId
    private RegistryKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    @JsonBackReference(value = "student-courses")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("class_id")
    @JoinColumn(name = "class_id")
    @JsonBackReference(value = "courses-students")
    private Course course;

    public Registry() {
    }

    public RegistryKey getId() {
        return id;
    }

    public void setId(RegistryKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
