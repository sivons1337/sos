package com.laa66.dao.impl;

import com.laa66.dao.PaymentDao;
import com.laa66.model.Payment;
import com.laa66.model.Student;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class PaymentDaoImpl implements PaymentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Payment getPayment(Integer paymentId) {
        return entityManager.find(Payment.class, paymentId);
    }

    @Override
    public Payment createPayment(Integer studentId, BigDecimal amount, LocalDate dueDate) {
        Student student = entityManager.find(Student.class, studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + studentId + " does not exist");
        }
        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setAmount(amount);
        payment.setDueDate(dueDate);
        payment.setStatus("PENDING");
        entityManager.persist(payment);
        return payment;
    }

    @Override
    public List<Payment> getUnpaidPaymentsByStudent(Integer studentId) {
        TypedQuery<Payment> q = entityManager.createQuery(
                "SELECT p FROM Payment p WHERE p.student.studentId = :sid AND (p.status IS NULL OR p.status <> 'PAID')",
                Payment.class);
        q.setParameter("sid", studentId);
        return q.getResultList();
    }
}