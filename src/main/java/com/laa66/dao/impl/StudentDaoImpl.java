package com.laa66.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.laa66.dao.StudentDao;
import com.laa66.model.Student;
import com.laa66.model.Student.StudentStatus;
import com.laa66.model.Role;

import java.time.LocalDate;

@Transactional
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void lockAccount(String email) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.email = :email", Student.class);
        query.setParameter("email", email);

        Student student = query.getSingleResult();
        student.setAccountStatus(StudentStatus.LOCKED);
        entityManager.merge(student);
    }

    @Override
    public void activateAccount(String email) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.email = :email", Student.class);
        query.setParameter("email", email);

        Student student = query.getSingleResult();
        student.setAccountStatus(StudentStatus.ACTIVE);
        entityManager.merge(student);
    }

    @Override
    public void changePassword(String email, String newPassword) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.email = :email", Student.class);
        query.setParameter("email", email);

        Student student = query.getSingleResult();
        student.setPasswordHash(newPassword);
        entityManager.merge(student);
    }

    @Override
    public Student createStudent(String name, String email, String firstName, String lastName, String indexNumber,
            String passwordHash, Role role) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setIndexNumber(indexNumber);
        student.setPasswordHash(passwordHash);
        student.setAccountStatus(StudentStatus.INACTIVE);
        student.setRegistrationDate(LocalDate.now());
        student.setRole(role);

        entityManager.persist(student);
        return student;
    }
}
