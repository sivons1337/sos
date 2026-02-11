package com.laa66.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

import com.laa66.dao.ClassSessionDao;
import com.laa66.model.ClassSession;
import com.laa66.model.Schedule;
import com.laa66.model.Course;

@Transactional
public class ClassSessionDaoImpl implements ClassSessionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ClassSession createClassSession(String dayOfWeek, LocalTime startTime, LocalTime endTime, String room, String form, Schedule schedule, Course course) {
        ClassSession classSession = new ClassSession();
        classSession.setDayOfWeek(dayOfWeek);
        classSession.setStartTime(startTime);
        classSession.setEndTime(endTime);
        classSession.setRoom(room);
        classSession.setForm(form);
        classSession.setSchedule(schedule);
        classSession.setCourse(course);

        entityManager.persist(classSession);
        return classSession;
    }

    @Override
    public ClassSession getClassSession(Integer classSessionId) {
        return entityManager.find(ClassSession.class, classSessionId);
    }

    @Override
    public List<ClassSession> getAllClassSessions() {
        TypedQuery<ClassSession> query = entityManager.createQuery("SELECT cs FROM ClassSession cs", ClassSession.class);
        return query.getResultList();
    }

    @Override
    public ClassSession updateClassSession(Integer classSessionId, String dayOfWeek, LocalTime startTime, LocalTime endTime, String room, String form) {
        ClassSession classSession = entityManager.find(ClassSession.class, classSessionId);
        if (classSession != null) {
            classSession.setDayOfWeek(dayOfWeek);
            classSession.setStartTime(startTime);
            classSession.setEndTime(endTime);
            classSession.setRoom(room);
            classSession.setForm(form);
            entityManager.merge(classSession);
        }
        return classSession;
    }

    @Override
    public void deleteClassSession(Integer classSessionId) {
        ClassSession classSession = entityManager.find(ClassSession.class, classSessionId);
        if (classSession != null) {
            entityManager.remove(classSession);
        }
    }

    @Override
    public List<ClassSession> getClassSessionsBySchedule(Integer scheduleId) {
        TypedQuery<ClassSession> query = entityManager.createQuery(
                "SELECT cs FROM ClassSession cs WHERE cs.schedule.scheduleId = :scheduleId", ClassSession.class);
        query.setParameter("scheduleId", scheduleId);
        return query.getResultList();
    }

    @Override
    public List<ClassSession> getClassSessionsByCourse(Integer courseId) {
        TypedQuery<ClassSession> query = entityManager.createQuery(
                "SELECT cs FROM ClassSession cs WHERE cs.course.courseId = :courseId", ClassSession.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }

    @Override
    public List<ClassSession> getClassSessionsByDay(String dayOfWeek) {
        TypedQuery<ClassSession> query = entityManager.createQuery(
                "SELECT cs FROM ClassSession cs WHERE cs.dayOfWeek = :dayOfWeek", ClassSession.class);
        query.setParameter("dayOfWeek", dayOfWeek);
        return query.getResultList();
    }

    @Override
    public List<ClassSession> getClassSessionsByRoom(String room) {
        TypedQuery<ClassSession> query = entityManager.createQuery(
                "SELECT cs FROM ClassSession cs WHERE cs.room = :room", ClassSession.class);
        query.setParameter("room", room);
        return query.getResultList();
    }
}