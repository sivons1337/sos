package com.laa66.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Integer loanId;

    @Column(name = "book_title", length = 255)
    private String bookTitle;

    @Column(name = "loan_date")
    private LocalDate loanDate;
    
    @Column(name = "return_deadline")
    private LocalDate returnDeadline;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Loan(Integer loanId, String bookTitle, LocalDate loanDate, LocalDate returnDeadline, Student student) {
        this.loanId = loanId;
        this.bookTitle = bookTitle;
        this.loanDate = loanDate;
        this.returnDeadline = returnDeadline;
        this.student = student;
    }

    public Loan() {
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDeadline() {
        return returnDeadline;
    }

    public void setReturnDeadline(LocalDate returnDeadline) {
        this.returnDeadline = returnDeadline;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}