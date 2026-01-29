package com.laa66.dao;

import com.laa66.model.Role;
import com.laa66.model.Student;

public interface StudentDao {
    void lockAccount(String email);

    void activateAccount(String email);

    void changePassword(String email, String newPassword);

    Student createStudent(String name, String email, String firstName, String lastName, String indexNumber,
            String passwordHash, Role role);
}
