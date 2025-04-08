package com.payment.service.core.ports;

import com.payment.service.core.domain.Payment;
import com.payment.service.core.usecases.payment.exception.PaymentNotFoundException;

import java.util.Optional;

public interface PaymentRepositoryPort {

    void save(Payment payment);

    Optional<Payment> getByOrderIdAndTransactionId(String orderId, String transactionId) throws PaymentNotFoundException;

    boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);
}
