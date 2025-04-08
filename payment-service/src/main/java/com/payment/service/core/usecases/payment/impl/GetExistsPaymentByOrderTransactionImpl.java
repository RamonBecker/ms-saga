package com.payment.service.core.usecases.payment.impl;

import com.payment.service.core.ports.PaymentRepositoryPort;
import com.payment.service.core.usecases.payment.GetExistsByOrderAndTransaction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class GetExistsPaymentByOrderTransactionImpl implements GetExistsByOrderAndTransaction {

    private PaymentRepositoryPort repository;

    @Override
    public boolean execute(String orderId, String transactionId) {
        return repository.existsByOrderIdAndTransactionId(orderId, transactionId);
    }
}
