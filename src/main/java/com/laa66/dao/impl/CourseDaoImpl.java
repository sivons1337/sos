package com.laa66.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import com.laa66.dao.CourseDao;
import com.laa66.model.Course;
import com.laa66.model.Enrollment;
import com.laa66.model.Grade;
import com.laa66.model.ClassSession;

@Transactional
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Course createCourse(String name, String description, Integer ects, Integer hours, Integer semester, Integer capacity) {
        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setEcts(ects);
        course.setHours(hours);
        course.setSemester(semester);
        course.setCapacity(capacity);

        entityManager.persist(course);
        return course;
    }

    @Override
    public Course getCourse(Integer courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
        return query.getResultList();
    }

    @Override
    public Course updateCourse(Integer courseId, String name, String description, Integer ects, Integer hours, Integer semester, Integer capacity) {
        Course course = entityManager.find(Course.class, courseId);
        if (course != null) {
            course.setName(name);
            course.setDescription(description);
            course.setEcts(ects);
            course.setHours(hours);
            course.setSemester(semester);
            course.setCapacity(capacity);
            entityManager.merge(course);
        }
        return course;
    }

    @Override
    public void deleteCourse(Integer courseId) {
        Course course = entityManager.find(Course.class, courseId);
        if (course != null) {
            entityManager.remove(course);
        }
    }

    @Override
    public List<Enrollment> getEnrollments(Integer courseId) {
        TypedQuery<Enrollment> query = entityManager.createQuery(
                "SELECT e FROM Enrollment e WHERE e.course.courseId = :courseId", Enrollment.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }

    @Override
    public List<Grade> getGrades(Integer courseId) {
        TypedQuery<Grade> query = entityManager.createQuery(
                "SELECT g FROM Grade g WHERE g.course.courseId = :courseId", Grade.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }

    @Override
    public List<ClassSession> getClassSessions(Integer courseId) {
        TypedQuery<ClassSession> query = entityManager.createQuery(
                "SELECT cs FROM ClassSession cs WHERE cs.course.courseId = :courseId", ClassSession.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }
}