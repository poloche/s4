package org.plc.interview.s4.controllers;

import org.plc.interview.s4.controllers.dto.CourseDTO;
import org.plc.interview.s4.domain.Course;
import org.plc.interview.s4.domain.repository.CourseRepository;
import org.plc.interview.s4.exceptions.ResourceNotFoundException;
import org.plc.interview.s4.utils.StringUtils;
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
    public Page<CourseDTO> all(Pageable pageable, @RequestParam(required = false) String title, @RequestParam(required = false) String description) {
        if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(description)) {
            return courseRepository.findByTitleContainingOrDescriptionContainingAllIgnoreCase(title, description, pageable).map(this::toDTO);
        } else if (StringUtils.isNotEmpty(title)) {
            return courseRepository.findByTitleContainingIgnoreCase(title, pageable).map(this::toDTO);
        } else if (StringUtils.isNotEmpty(description)) {
            return courseRepository.findByDescriptionContainingIgnoreCase(description, pageable).map(this::toDTO);
        }
        return courseRepository.findAll(pageable).map(this::toDTO);
    }


    @GetMapping(value = "/courses/{courseId}")
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

    @DeleteMapping(value = "/courses/{courseId}")
    public boolean deleteCourse(@PathVariable Integer courseId) {
        Course course = courseRepository.findById(courseId)

                .orElseThrow(() -> new ResourceNotFoundException("Course [courseId=" + courseId + "] can't be found"));
        courseRepository.delete(course);
        return true;
    }

    private CourseDTO toDTO(Course course) {

        CourseDTO dto = new CourseDTO();
        dto.setCode(course.getCode())
                .setTitle(course.getTitle())
                .setDescription(course.getDescription());
        course.getRegistries().forEach(registry -> dto.addStudent(registry.getStudent().getId()));

        return dto;

    }
}
