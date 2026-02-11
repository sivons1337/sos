package com.laa66.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import com.laa66.dao.EnrollmentDao;
import com.laa66.model.Enrollment;
import com.laa66.model.Student;
import com.laa66.model.Course;

@Transactional
public class EnrollmentDaoImpl implements EnrollmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Enrollment createEnrollment(LocalDate enrollmentDate, String status, Student student, Course course) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentDate(enrollmentDate);
        enrollment.setStatus(status);
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        entityManager.persist(enrollment);
        return enrollment;
    }

    @Override
    public Enrollment getEnrollment(Integer enrollmentId) {
        return entityManager.find(Enrollment.class, enrollmentId);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        TypedQuery<Enrollment> query = entityManager.createQuery("SELECT e FROM Enrollment e", Enrollment.class);
        return query.getResultList();
    }

    @Override
    public Enrollment updateEnrollment(Integer enrollmentId, LocalDate enrollmentDate, String status) {
        Enrollment enrollment = entityManager.find(Enrollment.class, enrollmentId);
        if (enrollment != null) {
            enrollment.setEnrollmentDate(enrollmentDate);
            enrollment.setStatus(status);
            entityManager.merge(enrollment);
        }
        return enrollment;
    }

    @Override
    public void deleteEnrollment(Integer enrollmentId) {
        Enrollment enrollment = entityManager.find(Enrollment.class, enrollmentId);
        if (enrollment != null) {
            entityManager.remove(enrollment);
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(Integer studentId) {
        TypedQuery<Enrollment> query = entityManager.createQuery(
                "SELECT e FROM Enrollment e WHERE e.student.studentId = :studentId", Enrollment.class);
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(Integer courseId) {
        TypedQuery<Enrollment> query = entityManager.createQuery(
                "SELECT e FROM Enrollment e WHERE e.course.courseId = :courseId", Enrollment.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }

    @Override
    public Enrollment getEnrollmentByStudentAndCourse(Integer studentId, Integer courseId) {
        TypedQuery<Enrollment> query = entityManager.createQuery(
                "SELECT e FROM Enrollment e WHERE e.student.studentId = :studentId AND e.course.courseId = :courseId", 
                Enrollment.class);
        query.setParameter("studentId", studentId);
        query.setParameter("courseId", courseId);
        List<Enrollment> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}