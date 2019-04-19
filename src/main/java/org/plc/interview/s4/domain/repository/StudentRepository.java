package org.plc.interview.s4.domain.repository;

import org.plc.interview.s4.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.DoubleStream;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findByFirstNameContainingAndLastNameContainingAllIgnoreCase(String firstName, String lastName, Pageable pageable);

    Page<Student> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);

    Page<Student> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
}
