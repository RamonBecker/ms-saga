package com.payment.service.core.usecases.payment;

public interface GetExistsByOrderAndTransaction {

    boolean execute(String orderId, String transactionId);
}
