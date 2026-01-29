package com.laa66.dao;

import com.laa66.model.Loan;

public interface LoanDao {
    Loan getLoan(Integer loanId);

    Loan createLoan(Integer studentId, String bookTitle, Double amount, Integer termMonths);

    void closeLoan(Integer loanId);
}
