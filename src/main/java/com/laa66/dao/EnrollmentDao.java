package com.laa66.dao;

import com.laa66.model.Enrollment;
import com.laa66.model.Student;
import com.laa66.model.Course;

import java.time.LocalDate;
import java.util.List;

public interface EnrollmentDao {
    Enrollment createEnrollment(LocalDate enrollmentDate, String status, Student student, Course course);

    Enrollment getEnrollment(Integer enrollmentId);

    List<Enrollment> getAllEnrollments();

    Enrollment updateEnrollment(Integer enrollmentId, LocalDate enrollmentDate, String status);

    void deleteEnrollment(Integer enrollmentId);

    List<Enrollment> getEnrollmentsByStudent(Integer studentId);

    List<Enrollment> getEnrollmentsByCourse(Integer courseId);

    Enrollment getEnrollmentByStudentAndCourse(Integer studentId, Integer courseId);
}