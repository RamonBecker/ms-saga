package com.product.validation.service.core.usecases.validation.impl;

import com.product.validation.service.core.domain.Event;
import com.product.validation.service.core.domain.Product;
import com.product.validation.service.core.domain.ProductValidation;
import com.product.validation.service.core.ports.ProductRepositoryPort;
import com.product.validation.service.core.ports.ProductValidationRepositoryPort;
import com.product.validation.service.core.usecases.product.exception.ProductValidationException;
import com.product.validation.service.core.usecases.validation.SaveProduct;
import com.product.validation.service.core.usecases.validation.exception.OrderValidationException;
import com.product.validation.service.core.usecases.validation.exception.TransactionValidationException;


public class SaveProductImpl implements SaveProduct {


    private ProductValidationRepositoryPort productValidationPort;
    private ProductRepositoryPort productPort;

    @Override
    public void execute(Event event) {

        if (event.getOrder() == null || event.getOrder().getId() == null)
            throw new OrderValidationException("Order cannot be null");
        if (event.getOrder().getProducts() == null || event.getOrder().getProducts().isEmpty())
            throw new ProductValidationException("Product list is empty!");
        if (event.getTransactionId() == null || event.getTransactionId().isEmpty())
            throw new TransactionValidationException("Transaction Id is empty!");

        if (productValidationPort.existsByOrderIdAndTransactionId(event.getOrder().getId(), event.getTransactionId()))
            throw new OrderValidationException("There's another transactionId for this validation.");


        event.getOrder().getProducts().forEach(
                orderProduct -> {
                    validateProduct(orderProduct.getProduct());
                }
        );


        var validation = ProductValidation
                .builder()
                .orderId(event.getOrder().getId())
                .transactionId(event.getTransactionId())
                .success(true)
                .build();

        productValidationPort.save(validation);

    }

    private void validateProduct(Product product) {
        if (product == null)
            throw new ProductValidationException("Product cannot be null");
        if (product.getCode() == null || product.getCode().isEmpty())
            throw new ProductValidationException("Product code must be informed!");
        if (productPort.existsByCode(product.getCode()))
            throw new ProductValidationException("Product already exists!");
    }
}
