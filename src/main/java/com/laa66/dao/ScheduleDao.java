package com.laa66.dao;

import com.laa66.model.Schedule;
import com.laa66.model.Student;
import com.laa66.model.ClassSession;

import java.util.List;

public interface ScheduleDao {
    Schedule createSchedule(Integer semester, Student student);

    Schedule getSchedule(Integer scheduleId);

    List<Schedule> getAllSchedules();

    Schedule updateSchedule(Integer scheduleId, Integer semester);

    void deleteSchedule(Integer scheduleId);

    Schedule getScheduleByStudent(Integer studentId);

    List<ClassSession> getClassSessionsForSchedule(Integer scheduleId);
}