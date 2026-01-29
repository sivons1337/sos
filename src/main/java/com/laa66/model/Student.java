package com.laa66.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 20)
    private String indexNumber;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private StudentStatus accountStatus;

    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public Student() {
    }
    // getters & setters
    public Student(Integer studentId, String firstName, String lastName, String indexNumber, String email,
            String passwordHash, StudentStatus accountStatus, LocalDate registrationDate, Role role) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
        this.email = email;
        this.passwordHash = passwordHash;
        this.accountStatus = accountStatus;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public static enum StudentStatus {
        ACTIVE,
        INACTIVE,
        LOCKED;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public StudentStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(StudentStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
