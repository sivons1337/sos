package com.laa66.dao;

import com.laa66.model.ClassSession;
import com.laa66.model.Schedule;
import com.laa66.model.Course;

import java.time.LocalTime;
import java.util.List;

public interface ClassSessionDao {
    ClassSession createClassSession(String dayOfWeek, LocalTime startTime, LocalTime endTime, String room, String form, Schedule schedule, Course course);

    ClassSession getClassSession(Integer classSessionId);

    List<ClassSession> getAllClassSessions();

    ClassSession updateClassSession(Integer classSessionId, String dayOfWeek, LocalTime startTime, LocalTime endTime, String room, String form);

    void deleteClassSession(Integer classSessionId);

    List<ClassSession> getClassSessionsBySchedule(Integer scheduleId);

    List<ClassSession> getClassSessionsByCourse(Integer courseId);

    List<ClassSession> getClassSessionsByDay(String dayOfWeek);

    List<ClassSession> getClassSessionsByRoom(String room);
}