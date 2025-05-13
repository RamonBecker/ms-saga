package com.product.validation.service.core.usecases.validation;


import com.product.validation.service.core.domain.product.ProductValidation;

import java.util.Optional;

public interface GetProductValidationByOrderAndTransaction {

    Optional<ProductValidation> execute(String orderId, String transactionId);

}
