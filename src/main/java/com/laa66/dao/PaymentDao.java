package com.laa66.dao;

import com.laa66.model.Payment;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PaymentDao {
    Payment getPayment(Integer paymentId);

    Payment createPayment(Integer studentId, BigDecimal amount, LocalDate dueDate);

    List<Payment> getUnpaidPaymentsByStudent(Integer studentId);
}