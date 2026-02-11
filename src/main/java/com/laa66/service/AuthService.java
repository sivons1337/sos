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
            Student student = studentDao.findByEmail(email);

            if (student != null) {
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
        Role studentRole = findOrCreateStudentRole();

        String hashedPassword = hashPassword(password);

        return studentDao.createStudent(
                firstName + " " + lastName,
                email,
                firstName,
                lastName,
                indexNumber,
                hashedPassword,
                studentRole
        );
    }

    private Role findOrCreateStudentRole() {
        Role role = new Role();
        role.setName("STUDENT");
        return role;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}