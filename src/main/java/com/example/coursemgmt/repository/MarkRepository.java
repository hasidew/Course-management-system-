package com.example.coursemgmt.repository;

import com.example.coursemgmt.entity.Mark;
import com.example.coursemgmt.entity.Student; // <-- import Student
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    boolean existsByStudentAndCourseCode(Student student, String courseCode);
}
