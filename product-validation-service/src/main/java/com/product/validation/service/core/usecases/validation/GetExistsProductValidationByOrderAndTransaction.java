package com.product.validation.service.core.usecases.validation;



public interface GetExistsProductValidationByOrderAndTransaction {

    Boolean execute(String orderId, String transactionId);

}
