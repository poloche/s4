package org.plc.interview.s4.controllers;

import org.plc.interview.s4.controllers.dto.StudentDTO;
import org.plc.interview.s4.domain.Student;
import org.plc.interview.s4.domain.repository.StudentRepository;
import org.plc.interview.s4.exceptions.ResourceNotFoundException;
import org.plc.interview.s4.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/students")
    public Page<StudentDTO> all(Pageable pageable, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        if (StringUtils.isNotEmpty(firstName) && StringUtils.isNotEmpty(lastName)) {
            return studentRepository.findByFirstNameContainingAndLastNameContainingAllIgnoreCase(firstName, lastName, pageable).map(this::toDTO);
        } else if (StringUtils.isNotEmpty(firstName)) {
            return studentRepository.findByFirstNameContainingIgnoreCase(firstName, pageable).map(this::toDTO);
        } else if (StringUtils.isNotEmpty(lastName)) {
            return studentRepository.findByLastNameContainingIgnoreCase(lastName, pageable).map(this::toDTO);
        }

        return studentRepository.findAll(pageable).map(this::toDTO);
    }


    @GetMapping(value = "/students/{studentId}")
    public StudentDTO findByStudentId(@PathVariable Integer studentId) {
        return studentRepository.findById(studentId)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Student [studentId=" + studentId + "] can't be found"));
    }

    @DeleteMapping(value = "/students/{studentId}")
    public boolean deleteStudent(@PathVariable Integer studentId) {
        Student student = studentRepository.findById(studentId)

                .orElseThrow(() -> new ResourceNotFoundException("Student [studentId=" + studentId + "] can't be found"));
        studentRepository.delete(student);
        return true;
    }

    @PostMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Student save(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    private StudentDTO toDTO(Student student) {

        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId())
                .setFistName(student.getFirstName())
                .setLastName(student.getLastName());
        student.getRegistries().forEach(registry -> dto.addCourse(registry.getCourse().getCode()));

        return dto;

    }
}
