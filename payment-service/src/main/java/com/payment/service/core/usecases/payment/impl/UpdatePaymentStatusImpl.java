package com.payment.service.core.usecases.payment.impl;

import com.payment.service.core.domain.Event;
import com.payment.service.core.ports.PaymentRepositoryPort;
import com.payment.service.core.usecases.payment.UpdatePaymentStatus;
import com.payment.service.core.usecases.payment.exception.AmountValidationException;
import com.payment.service.core.usecases.payment.exception.PaymentNotFoundException;
import com.payment.service.infrastructure.shared.constants.SagaStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentStatusImpl implements UpdatePaymentStatus {

    private PaymentRepositoryPort repository;

    @Override
    public void execute(SagaStatus status, Event event) throws PaymentNotFoundException {

        repository.getByOrderIdAndTransactionId(event.getOrderId(), event.getTransactionId()).ifPresent(
                payment -> {
                    try {
                        payment.validateAmount();
                    } catch (AmountValidationException e) {
                        throw new RuntimeException(e);
                    }
                    payment.setStatus(String.valueOf(status));
                    repository.save(payment);
                }
        );
    }
}
