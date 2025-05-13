package com.payment.service.core.usecases.payment;

import com.payment.service.core.domain.payment.Payment;
import com.payment.service.core.usecases.payment.exception.PaymentNotFoundException;

public interface GetPaymentByOrderAndTransaction {

    Payment execute(String orderId, String transactionId) throws PaymentNotFoundException;
}
