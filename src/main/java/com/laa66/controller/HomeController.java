package com.laa66.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
            <h1>SOS - Student Information System API</h1>
            <p>Welcome to the Student Information System API!</p>
            
            <h2>Available Endpoints:</h2>
            <ul>
                <li><strong>Students:</strong> GET /api/students/{id}, POST /api/students</li>
                <li><strong>Student Enrollments:</strong> GET /api/students/{id}/enrollments</li>
                <li><strong>Student Grades:</strong> GET /api/students/{id}/grades</li>
                <li><strong>Account Management:</strong> PUT /api/students/{id}/lock, PUT /api/students/{id}/activate</li>
                <li><strong>Loans:</strong> GET /api/loans/{id}, POST /api/loans</li>
                <li><strong>Payments:</strong> GET /api/payments/{id}, POST /api/payments</li>
            </ul>
            """;
    }
}