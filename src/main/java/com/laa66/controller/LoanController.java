package com.laa66.controller;

import com.laa66.dao.LoanDao;
import com.laa66.model.Loan;
import com.laa66.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanDao loanDao;

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoan(@PathVariable Integer id) {
        Loan loan = loanDao.getLoan(id);
        if (loan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loan);
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody CreateLoanRequest request) {
        Loan loan = loanDao.createLoan(
                request.getStudentId(),
                request.getBookTitle(),
                request.getTermMonths()
        );
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Loan>> getLoansByStudent(@PathVariable Integer studentId) {
        List<Loan> loans = loanDao.getLoansByStudent(studentId);
        return ResponseEntity.ok(loans);
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<Void> closeLoan(@PathVariable Integer id) {
        loanDao.closeLoan(id);
        return ResponseEntity.ok().build();
    }

    // Inner class for request body
    public static class CreateLoanRequest {
        private Integer studentId;
        private String bookTitle;
        private Integer termMonths;

        // Getters and setters
        public Integer getStudentId() { return studentId; }
        public void setStudentId(Integer studentId) { this.studentId = studentId; }
        
        public String getBookTitle() { return bookTitle; }
        public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
        
        public Integer getTermMonths() { return termMonths; }
        public void setTermMonths(Integer termMonths) { this.termMonths = termMonths; }
    }
}