package org.plc.interview.s4.domain.repository;

import org.plc.interview.s4.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
