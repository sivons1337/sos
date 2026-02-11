package com.laa66.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.laa66.dao.GradeDao;
import com.laa66.model.Grade;
import com.laa66.model.Student;
import com.laa66.model.Course;

@Transactional
public class GradeDaoImpl implements GradeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Grade createGrade(BigDecimal value, String type, LocalDate issueDate, Student student, Course course) {
        Grade grade = new Grade();
        grade.setValue(value);
        grade.setType(type);
        grade.setIssueDate(issueDate);
        grade.setStudent(student);
        grade.setCourse(course);

        entityManager.persist(grade);
        return grade;
    }

    @Override
    public Grade getGrade(Integer gradeId) {
        return entityManager.find(Grade.class, gradeId);
    }

    @Override
    public List<Grade> getAllGrades() {
        TypedQuery<Grade> query = entityManager.createQuery("SELECT g FROM Grade g", Grade.class);
        return query.getResultList();
    }

    @Override
    public Grade updateGrade(Integer gradeId, BigDecimal value, String type, LocalDate issueDate) {
        Grade grade = entityManager.find(Grade.class, gradeId);
        if (grade != null) {
            grade.setValue(value);
            grade.setType(type);
            grade.setIssueDate(issueDate);
            entityManager.merge(grade);
        }
        return grade;
    }

    @Override
    public void deleteGrade(Integer gradeId) {
        Grade grade = entityManager.find(Grade.class, gradeId);
        if (grade != null) {
            entityManager.remove(grade);
        }
    }

    @Override
    public List<Grade> getGradesByStudent(Integer studentId) {
        TypedQuery<Grade> query = entityManager.createQuery(
                "SELECT g FROM Grade g WHERE g.student.studentId = :studentId", Grade.class);
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }

    @Override
    public List<Grade> getGradesByCourse(Integer courseId) {
        TypedQuery<Grade> query = entityManager.createQuery(
                "SELECT g FROM Grade g WHERE g.course.courseId = :courseId", Grade.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }

    @Override
    public List<Grade> getGradesByStudentAndCourse(Integer studentId, Integer courseId) {
        TypedQuery<Grade> query = entityManager.createQuery(
                "SELECT g FROM Grade g WHERE g.student.studentId = :studentId AND g.course.courseId = :courseId", 
                Grade.class);
        query.setParameter("studentId", studentId);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }

    @Override
    public List<Grade> getGradesByType(String type) {
        TypedQuery<Grade> query = entityManager.createQuery(
                "SELECT g FROM Grade g WHERE g.type = :type", Grade.class);
        query.setParameter("type", type);
        return query.getResultList();
    }
}