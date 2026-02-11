package com.laa66.dao.impl;

import com.laa66.dao.LoanDao;
import com.laa66.model.Loan;
import com.laa66.model.Student;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class LoanDaoImpl implements LoanDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Loan getLoan(Integer loanId) {
        return entityManager.find(Loan.class, loanId);
    }

    @Override
    public Loan createLoan(Integer studentId, String bookTitle, Integer termMonths) {
        Student student = entityManager.find(Student.class, studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + studentId + " does not exist");
        }

        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setBookTitle(bookTitle);

        LocalDate today = LocalDate.now();
        loan.setLoanDate(today);
        loan.setReturnDeadline(today.plusMonths(termMonths));

        entityManager.persist(loan);

        return loan;
    }

    @Override
    public List<Loan> getLoansByStudent(Integer studentId) {
        TypedQuery<Loan> q = entityManager.createQuery(
                "SELECT l FROM Loan l WHERE l.student.studentId = :sid", Loan.class);
        q.setParameter("sid", studentId);
        return q.getResultList();
    }

    @Override
    public void closeLoan(Integer loanId) {
        Loan loan = entityManager.find(Loan.class, loanId);
        if (loan != null) {
            loan.setReturnDeadline(LocalDate.now());
            entityManager.merge(loan);
        } else {
            throw new IllegalArgumentException("Loan with ID " + loanId + " does not exist.");
        }
    }
}