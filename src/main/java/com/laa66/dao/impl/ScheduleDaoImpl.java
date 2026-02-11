package com.laa66.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import com.laa66.dao.ScheduleDao;
import com.laa66.model.Schedule;
import com.laa66.model.Student;
import com.laa66.model.ClassSession;

@Transactional
public class ScheduleDaoImpl implements ScheduleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Schedule createSchedule(Integer semester, Student student) {
        Schedule schedule = new Schedule();
        schedule.setSemester(semester);
        schedule.setStudent(student);

        entityManager.persist(schedule);
        return schedule;
    }

    @Override
    public Schedule getSchedule(Integer scheduleId) {
        return entityManager.find(Schedule.class, scheduleId);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT s FROM Schedule s", Schedule.class);
        return query.getResultList();
    }

    @Override
    public Schedule updateSchedule(Integer scheduleId, Integer semester) {
        Schedule schedule = entityManager.find(Schedule.class, scheduleId);
        if (schedule != null) {
            schedule.setSemester(semester);
            entityManager.merge(schedule);
        }
        return schedule;
    }

    @Override
    public void deleteSchedule(Integer scheduleId) {
        Schedule schedule = entityManager.find(Schedule.class, scheduleId);
        if (schedule != null) {
            entityManager.remove(schedule);
        }
    }

    @Override
    public Schedule getScheduleByStudent(Integer studentId) {
        TypedQuery<Schedule> query = entityManager.createQuery(
                "SELECT s FROM Schedule s WHERE s.student.studentId = :studentId", Schedule.class);
        query.setParameter("studentId", studentId);
        List<Schedule> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<ClassSession> getClassSessionsForSchedule(Integer scheduleId) {
        TypedQuery<ClassSession> query = entityManager.createQuery(
                "SELECT cs FROM ClassSession cs WHERE cs.schedule.scheduleId = :scheduleId", ClassSession.class);
        query.setParameter("scheduleId", scheduleId);
        return query.getResultList();
    }
}