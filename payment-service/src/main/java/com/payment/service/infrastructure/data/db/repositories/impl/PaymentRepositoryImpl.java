package com.payment.service.infrastructure.data.db.repositories.impl;

import com.payment.service.core.domain.payment.Payment;
import com.payment.service.core.ports.PaymentRepositoryPort;
import com.payment.service.core.usecases.payment.exception.PaymentNotFoundException;
import com.payment.service.infrastructure.data.db.entities.PaymentEntity;
import com.payment.service.infrastructure.data.db.repositories.JpaPaymentRepository;

import java.util.Optional;

public class PaymentRepositoryImpl implements PaymentRepositoryPort {

    private final JpaPaymentRepository repository;

    public PaymentRepositoryImpl(JpaPaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Payment payment) {
        repository.save(PaymentEntity.fromEntity(payment));
    }

    @Override
    public Optional<Payment> getByOrderIdAndTransactionId(String orderId, String transactionId) throws PaymentNotFoundException {
        return repository.getByOrderIdAndTransactionId(orderId, transactionId).map(Payment::fromDomain);
    }

    @Override
    public boolean existsByOrderIdAndTransactionId(String orderId, String transactionId) {
        return repository.existsByOrderIdAndTransactionId(orderId, transactionId);
    }
}
