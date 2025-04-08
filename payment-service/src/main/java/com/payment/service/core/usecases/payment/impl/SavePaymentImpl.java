package com.payment.service.core.usecases.payment.impl;

import com.payment.service.core.domain.Event;
import com.payment.service.core.domain.Payment;
import com.payment.service.core.ports.PaymentRepositoryPort;
import com.payment.service.core.usecases.payment.SavePayment;
import com.payment.service.core.usecases.payment.exception.PaymentExistsException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class SavePaymentImpl implements SavePayment {

    private PaymentRepositoryPort repository;

    @Override
    public void execute(Event event) throws PaymentExistsException {

        if (repository.existsByOrderIdAndTransactionId(event.getOrderId(), event.getTransactionId()))
            throw new PaymentExistsException("There's another transactionId for this validation.");

        repository.save(Payment.builder()
                .orderId(event.getOrderId())
                .transactionId(event.getTransactionId())
                .totalItems(event.getOrder().calculateTotalItems())
                .totalAmount(event.getOrder().calculateAmount())
                .build());
    }
}
