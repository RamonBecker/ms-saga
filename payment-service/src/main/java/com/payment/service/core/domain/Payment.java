package com.payment.service.core.domain;

import com.payment.service.core.usecases.payment.exception.AmountValidationException;
import com.payment.service.infrastructure.data.db.entities.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.payment.service.infrastructure.shared.constants.ValueOperationCalculation.MIN_AMOUNT;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Payment {

    private Integer id;

    private String orderId;

    private String transactionId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int totalItems;

    private double totalAmount;

    private String status;

    public static Payment fromDomain(PaymentEntity entity) {
        return Payment.builder().
                id(entity.getId()).
                orderId(entity.getOrderId()).
                createdAt(entity.getCreatedAt()).
                updatedAt(entity.getUpdatedAt()).
                transactionId(entity.getTransactionId()).
                totalItems(entity.getTotalItems()).
                totalAmount(entity.getTotalAmount()).
                status(entity.getStatus()).
                build();
    }

    public void validateAmount() throws AmountValidationException {
        if (getTotalAmount() < MIN_AMOUNT.getValue())
            throw new AmountValidationException("The minimal amount available is {}".concat(MIN_AMOUNT.getValue().toString()));
    }


}
