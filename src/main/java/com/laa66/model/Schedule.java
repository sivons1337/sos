package com.laa66.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    private Integer semester;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false, unique = true)
    private Student student;


    @OneToMany(mappedBy = "schedule")
    private List<ClassSession> classSessions = new ArrayList<>();

    public Schedule() {
    }

    public Schedule(Integer scheduleId, Integer semester, Student student) {
        this.scheduleId = scheduleId;
        this.semester = semester;
        this.student = student;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
