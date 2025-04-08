package com.payment.service.core.usecases.payment.impl;

import com.payment.service.core.domain.Payment;
import com.payment.service.core.ports.PaymentRepositoryPort;
import com.payment.service.core.usecases.payment.GetPaymentByOrderAndTransaction;
import com.payment.service.core.usecases.payment.exception.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class GetPaymentByOrderTransactionImpl implements GetPaymentByOrderAndTransaction {

    private PaymentRepositoryPort repository;

    @Override
    public Payment execute(String orderId, String transactionId) throws PaymentNotFoundException {

        return repository.getByOrderIdAndTransactionId(orderId, transactionId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }
}
