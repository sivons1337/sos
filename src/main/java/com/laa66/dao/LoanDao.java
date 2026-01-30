package com.laa66.dao;

import com.laa66.model.Loan;
import java.util.List;

public interface LoanDao {
    Loan getLoan(Integer loanId);

    Loan createLoan(Integer studentId, String bookTitle, Integer termMonths);

    List<Loan> getLoansByStudent(Integer studentId);

    void closeLoan(Integer loanId);
}