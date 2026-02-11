package com.laa66.dao;

import com.laa66.model.Course;
import com.laa66.model.Enrollment;
import com.laa66.model.Grade;
import com.laa66.model.ClassSession;

import java.util.List;

public interface CourseDao {
    Course createCourse(String name, String description, Integer ects, Integer hours, Integer semester, Integer capacity);

    Course getCourse(Integer courseId);

    List<Course> getAllCourses();

    Course updateCourse(Integer courseId, String name, String description, Integer ects, Integer hours, Integer semester, Integer capacity);

    void deleteCourse(Integer courseId);

    List<Enrollment> getEnrollments(Integer courseId);

    List<Grade> getGrades(Integer courseId);

    List<ClassSession> getClassSessions(Integer courseId);
}