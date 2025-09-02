package com.example.coursemgmt.repository;

import com.example.coursemgmt.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
     boolean existsByCode(String code);
}
