package com.example.coursemgmt.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String courseCode;

    private int marks;
    private double gpa;
    private String grade;

    public Mark() {}

    public Mark(Student student, String courseCode, int marks, double gpa, String grade) {
        this.student = student;
        this.courseCode = courseCode;
        this.marks = marks;
        this.gpa = gpa;
        this.grade = grade;
    }


    public void calculateGpaAndGrade() {
    if (marks >= 85) {
        gpa = 4.0;
        grade = "A";
    } else if (marks >= 75) {
        gpa = 3.5;
        grade = "B+";
    } else if (marks >= 65) {
        gpa = 3.0;
        grade = "B";
    } else if (marks >= 55) {
        gpa = 2.5;
        grade = "C";
    } else if (marks >= 40) {
        gpa = 2.0;
        grade = "D";
    } else {
        gpa = 0.0;
        grade = "F";
    }
}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
