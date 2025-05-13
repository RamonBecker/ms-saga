package com.product.validation.service.core.usecases.validation.impl;

import com.product.validation.service.core.domain.event.Event;
import com.product.validation.service.core.ports.ProductValidationRepositoryPort;
import com.product.validation.service.core.usecases.validation.UpdateProductValidation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductValidationImpl implements UpdateProductValidation {

    private ProductValidationRepositoryPort productValidationPort;

    @Override
    public void execute(Event event, boolean isSuccess) {

        productValidationPort.findByOrderIdAndTransactionId(event.getOrderId(), event.getTransactionId())
                .ifPresent(productValidation -> {

                    productValidation.setSuccess(isSuccess);
                    productValidationPort.save(productValidation);
                });
    }
}
