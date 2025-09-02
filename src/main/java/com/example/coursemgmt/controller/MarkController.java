package com.example.coursemgmt.controller;

import com.example.coursemgmt.entity.Mark;
import com.example.coursemgmt.entity.Student;
import com.example.coursemgmt.repository.MarkRepository;
import com.example.coursemgmt.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
@CrossOrigin(origins = "http://localhost:3000") // React frontend
public class MarkController {

    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;

    public MarkController(MarkRepository markRepository, StudentRepository studentRepository) {
        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Mark> getAllMarks() {
        return markRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addMark(@RequestBody Mark mark) {
        // Ensure Student exists
        Student student = studentRepository.findById(mark.getStudent().getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Check for duplicate: same student + same course
        boolean exists = markRepository.existsByStudentAndCourseCode(student, mark.getCourseCode());
        if (exists) {
            return ResponseEntity
                    .badRequest()
                    .body("Mark for this student and course already exists.");
        }

        mark.setStudent(student);
        mark.calculateGpaAndGrade(); // calculate GPA and Grade
        Mark savedMark = markRepository.save(mark);

        return ResponseEntity.ok(savedMark);
    }
}
