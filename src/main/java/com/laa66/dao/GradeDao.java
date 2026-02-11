package com.laa66.dao;

import com.laa66.model.Grade;
import com.laa66.model.Student;
import com.laa66.model.Course;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface GradeDao {
    Grade createGrade(BigDecimal value, String type, LocalDate issueDate, Student student, Course course);

    Grade getGrade(Integer gradeId);

    List<Grade> getAllGrades();

    Grade updateGrade(Integer gradeId, BigDecimal value, String type, LocalDate issueDate);

    void deleteGrade(Integer gradeId);

    List<Grade> getGradesByStudent(Integer studentId);

    List<Grade> getGradesByCourse(Integer courseId);

    List<Grade> getGradesByStudentAndCourse(Integer studentId, Integer courseId);

    List<Grade> getGradesByType(String type);
}