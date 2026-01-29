package com.laa66.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.laa66.dao.PaymentDao;
import com.laa66.model.Payment;

@Transactional
public class PaymentDaoImpl implements PaymentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Payment getPayment(Integer paymentId) {
        return entityManager.find(Payment.class, paymentId);
    }

}
