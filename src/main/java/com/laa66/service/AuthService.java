package com.laa66.service;

import com.laa66.dao.StudentDao;
import com.laa66.model.Role;
import com.laa66.model.Student;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private StudentDao studentDao;

    public Optional<Student> authenticate(String email, String password) {
        try {
            // Find the student by email
            Student student = studentDao.findByEmail(email);
            
            if (student != null) {
                // Verify the password using BCrypt
                if (verifyPassword(password, student.getPasswordHash())) {
                    return Optional.of(student);
                }
            }
            
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Student registerStudent(String firstName, String lastName, String email, String indexNumber, String password) {
        // Find or create the STUDENT role
        Role studentRole = findOrCreateStudentRole();
        
        // Hash the password
        String hashedPassword = hashPassword(password);
        
        return studentDao.createStudent(
                firstName + " " + lastName, // Full name
                email,
                firstName,
                lastName,
                indexNumber,
                hashedPassword,
                studentRole
        );
    }
    
    private Role findOrCreateStudentRole() {
        // Create a role with just the name - the StudentDaoImpl will look up the managed entity
        Role role = new Role();
        role.setName("STUDENT");
        return role;
    }

    private String hashPassword(String password) {
        // Use BCrypt to hash the password
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        // Verify the plain password against the hashed password
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}