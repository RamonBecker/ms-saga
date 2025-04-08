package com.payment.service.core.usecases.payment;

import com.payment.service.core.domain.Event;
import com.payment.service.core.usecases.payment.exception.AmountValidationException;
import com.payment.service.core.usecases.payment.exception.PaymentNotFoundException;
import com.payment.service.infrastructure.shared.constants.SagaStatus;

public interface UpdatePaymentStatus {

    void execute(SagaStatus status, Event event) throws PaymentNotFoundException, AmountValidationException;
}
