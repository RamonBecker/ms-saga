package com.payment.service.core.usecases.payment;

import com.payment.service.core.domain.event.Event;
import com.payment.service.core.usecases.payment.exception.AmountValidationException;
import com.payment.service.core.usecases.payment.exception.PaymentNotFoundException;
import com.payment.service.infrastructure.shared.constants.Status;

public interface UpdatePaymentStatus {

    void execute(Status status, Event event) throws PaymentNotFoundException, AmountValidationException;
}
