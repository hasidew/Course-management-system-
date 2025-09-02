package com.example.coursemgmt.controller;

import com.example.coursemgmt.entity.Student;
import com.example.coursemgmt.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentRepository studentRepo;

    public StudentController(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    // Get all students
    @GetMapping
    public List<Student> all() {
        return studentRepo.findAll();
    }

    // Add a new student
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Student student) {
        // Check for duplicate studentNumber
        if (studentRepo.existsByStudentNumber(student.getStudentNumber())) {
            return ResponseEntity
                    .badRequest()
                    .body("Student with student number '" + student.getStudentNumber() + "' already exists.");
        }

        Student savedStudent = studentRepo.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    // Delete a student by ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentRepo.deleteById(id);
    }
}
