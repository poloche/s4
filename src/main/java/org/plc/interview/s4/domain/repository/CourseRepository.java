package org.plc.interview.s4.domain.repository;

import org.plc.interview.s4.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.DoubleStream;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Page<Course> findByTitleContainingOrDescriptionContainingAllIgnoreCase(String title, String description, Pageable pageable);

    Page<Course> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Course> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);
}
