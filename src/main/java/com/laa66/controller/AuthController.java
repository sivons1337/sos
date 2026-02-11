package com.laa66.controller;

import com.laa66.model.Student;
import com.laa66.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        var authResult = authService.authenticate(request.getEmail(), request.getPassword());
        
        if (authResult.isPresent()) {
            Student student = authResult.get();
            response.put("success", true);
            response.put("message", "Login successful");
            response.put("user", Map.of(
                "id", student.getStudentId(),
                "email", student.getEmail(),
                "firstName", student.getFirstName(),
                "lastName", student.getLastName(),
                "indexNumber", student.getIndexNumber()
            ));
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Student student = authService.registerStudent(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getIndexNumber(),
                request.getPassword()
            );
            
            response.put("success", true);
            response.put("message", "Registration successful");
            response.put("student", student);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Registration failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class RegisterRequest {
        private String firstName;
        private String lastName;
        private String email;
        private String indexNumber;
        private String password;

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getIndexNumber() { return indexNumber; }
        public void setIndexNumber(String indexNumber) { this.indexNumber = indexNumber; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}