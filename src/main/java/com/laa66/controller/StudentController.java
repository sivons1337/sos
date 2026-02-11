package com.laa66.controller;

import com.laa66.dao.StudentDao;
import com.laa66.model.Enrollment;
import com.laa66.model.Grade;
import com.laa66.model.Student;
import com.laa66.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody CreateStudentRequest request) {
        Student student = studentDao.createStudent(
                request.getName(),
                request.getEmail(),
                request.getFirstName(),
                request.getLastName(),
                request.getIndexNumber(),
                request.getPasswordHash(),
                request.getRole()
        );
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
        Student student = studentDao.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}/lock")
    public ResponseEntity<Void> lockAccount(@PathVariable String id) {
        studentDao.lockAccount(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activateAccount(@PathVariable String id) {
        studentDao.activateAccount(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable String id, @RequestParam String newPassword) {
        studentDao.changePassword(id, newPassword);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/enrollments")
    public ResponseEntity<List<Enrollment>> getEnrollments(@PathVariable Integer id) {
        List<Enrollment> enrollments = studentDao.getEnrollments(id);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/{id}/grades")
    public ResponseEntity<List<Grade>> getGrades(@PathVariable Integer id) {
        List<Grade> grades = studentDao.getGrades(id);
        return ResponseEntity.ok(grades);
    }

    // Inner class for request body
    public static class CreateStudentRequest {
        private String name;
        private String email;
        private String firstName;
        private String lastName;
        private String indexNumber;
        private String passwordHash;
        private Role role;

        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        
        public String getIndexNumber() { return indexNumber; }
        public void setIndexNumber(String indexNumber) { this.indexNumber = indexNumber; }
        
        public String getPasswordHash() { return passwordHash; }
        public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
        
        public Role getRole() { return role; }
        public void setRole(Role role) { this.role = role; }
    }
}