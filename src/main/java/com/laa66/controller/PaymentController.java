package com.laa66.controller;

import com.laa66.dao.PaymentDao;
import com.laa66.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentDao paymentDao;

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable Integer id) {
        Payment payment = paymentDao.getPayment(id);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody CreatePaymentRequest request) {
        Payment payment = paymentDao.createPayment(
                request.getStudentId(),
                request.getAmount(),
                request.getDueDate()
        );
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/student/{studentId}/unpaid")
    public ResponseEntity<List<Payment>> getUnpaidPaymentsByStudent(@PathVariable Integer studentId) {
        List<Payment> payments = paymentDao.getUnpaidPaymentsByStudent(studentId);
        return ResponseEntity.ok(payments);
    }

    // Inner class for request body
    public static class CreatePaymentRequest {
        private Integer studentId;
        private BigDecimal amount;
        private LocalDate dueDate;

        // Getters and setters
        public Integer getStudentId() { return studentId; }
        public void setStudentId(Integer studentId) { this.studentId = studentId; }
        
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        
        public LocalDate getDueDate() { return dueDate; }
        public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    }
}