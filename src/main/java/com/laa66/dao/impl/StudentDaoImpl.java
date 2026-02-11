package com.laa66.dao.impl;

import com.laa66.dao.StudentDao;
import com.laa66.model.Enrollment;
import com.laa66.model.Grade;
import com.laa66.model.Role;
import com.laa66.model.Student;
import com.laa66.model.Student.StudentStatus;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Repository
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
        // If the role doesn't have an ID, we need to fetch the existing role from the database
        Role managedRole;
        if (role.getRoleId() == null) {
            // Try to find the role by name
            TypedQuery<Role> roleQuery = entityManager.createQuery(
                "SELECT r FROM Role r WHERE r.name = :name", Role.class);
            roleQuery.setParameter("name", role.getName());
            List<Role> results = roleQuery.getResultList();
            
            if (!results.isEmpty()) {
                managedRole = results.get(0);
            } else {
                // If role doesn't exist, create a basic one with a known ID
                // In a real application, roles should be pre-populated in the database
                managedRole = new Role();
                managedRole.setRoleId(1); // Default to first role
                managedRole.setName("STUDENT");
            }
        } else {
            managedRole = role;
        }

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setIndexNumber(indexNumber);
        student.setPasswordHash(passwordHash);
        student.setAccountStatus(StudentStatus.INACTIVE);
        student.setRegistrationDate(LocalDate.now());
        student.setRole(managedRole);

        entityManager.persist(student);
        return student;
    }

    @Override
    public Student getStudent(Integer studentId) {
        return entityManager.find(Student.class, studentId);
    }
    
    @Override
    public Student findByEmail(String email) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.email = :email", Student.class);
        query.setParameter("email", email);
        
        List<Student> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    @Override
    public List<Enrollment> getEnrollments(Integer studentId) {
        TypedQuery<Enrollment> q = entityManager.createQuery(
                "SELECT e FROM Enrollment e WHERE e.student.studentId = :sid", Enrollment.class);
        q.setParameter("sid", studentId);
        return q.getResultList();
    }

    @Override
    public List<Grade> getGrades(Integer studentId) {
        TypedQuery<Grade> q = entityManager.createQuery(
                "SELECT g FROM Grade g WHERE g.student.studentId = :sid", Grade.class);
        q.setParameter("sid", studentId);
        return q.getResultList();
    }
}
