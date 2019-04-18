package org.plc.interview.s4.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(schema = "public", name = "class")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code")
public class Course implements Serializable {
    @Id
    @SequenceGenerator(name = "course_id_seq",
            sequenceName = "course_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "course_id_seq")
    private int code;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference(value = "courses-students")
    private Set<Registry> students;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Registry> getRegistries() {
        return students;
    }

    public void setStudents(Set<Registry> students) {
        this.students = students;
    }
}
