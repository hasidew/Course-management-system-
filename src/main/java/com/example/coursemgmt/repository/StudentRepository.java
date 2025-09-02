package com.example.coursemgmt.repository;

import com.example.coursemgmt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByStudentNumber(String studentNumber);
}
