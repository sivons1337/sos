package com.laa66.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "ClassSession")
public class ClassSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_session_id")
    private Integer classSessionId;

    @Column(name = "day_of_week", length = 20)
    private String dayOfWeek;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(length = 30)
    private String room;

    @Column(length = 30)
    private String form;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public ClassSession() {

    }

    public ClassSession(Integer classSessionId, String dayOfWeek, LocalTime startTime, LocalTime endTime, String room, String form, Schedule schedule, Course course) {
        this.classSessionId = classSessionId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.form = form;
        this.schedule = schedule;
        this.course = course;
    }

    public Integer getClassSessionId() {
        return classSessionId;
    }

    public void setClassSessionId(Integer classSessionId) {
        this.classSessionId = classSessionId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}