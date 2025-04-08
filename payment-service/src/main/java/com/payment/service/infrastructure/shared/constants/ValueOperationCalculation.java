package com.payment.service.infrastructure.shared.constants;

import lombok.Getter;

@Getter
public enum ValueOperationCalculation {

    REDUCE_SUM(0.0),
    MIN_AMOUNT(0.1);

    private final Double value;

    ValueOperationCalculation(Double value) {
        this.value = value;
    }
}
