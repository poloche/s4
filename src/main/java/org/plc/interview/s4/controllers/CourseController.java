package org.plc.interview.s4.controllers;

import org.plc.interview.s4.controllers.dto.CourseDTO;
import org.plc.interview.s4.domain.Course;
import org.plc.interview.s4.domain.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "/courses")
    public Page<CourseDTO> all(Pageable pageable) {
        return courseRepository.findAll(pageable).map(this::toDTO);
    }


    @GetMapping(value = "/course/{courseId}")
    public CourseDTO findByStudentId(@PathVariable Integer courseId) {
        return courseRepository.findById(courseId)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Course [CourseId=" + courseId + "] can't be found"));
    }

    @PostMapping(value = "/courses")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course save(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    private CourseDTO toDTO(Course course) {

        CourseDTO dto = new CourseDTO();
        dto.setCode(course.getCode())
                .setTitle(course.getTitle())
                .setDescription(course.getDescription());
        course.getRegistries().forEach(registry -> dto.addStudent(registry.getCourse().getCode()));

        return dto;

    }
}
