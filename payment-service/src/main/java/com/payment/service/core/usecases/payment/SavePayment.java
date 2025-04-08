package com.payment.service.core.usecases.payment;

import com.payment.service.core.domain.Event;
import com.payment.service.core.usecases.payment.exception.PaymentExistsException;

public interface SavePayment {

    void execute(Event event) throws PaymentExistsException;
}
