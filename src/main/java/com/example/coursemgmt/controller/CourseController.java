package com.example.coursemgmt.controller;

import com.example.coursemgmt.model.Course;
import com.example.coursemgmt.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        // Check if a course with the same code already exists
        if (courseRepository.existsByCode(course.getCode())) {
            return ResponseEntity
                    .badRequest()
                    .body("Course with code '" + course.getCode() + "' already exists.");
        }

        Course savedCourse = courseRepository.save(course);
        return ResponseEntity.ok(savedCourse);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }
}
