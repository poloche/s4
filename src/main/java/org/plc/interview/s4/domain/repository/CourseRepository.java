package org.plc.interview.s4.domain.repository;

import org.plc.interview.s4.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
