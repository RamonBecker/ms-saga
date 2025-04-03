package com.product.validation.service.core.usecases.product.impl;


import com.product.validation.service.core.ports.ProductRepositoryPort;
import com.product.validation.service.core.usecases.product.GetExistsProduct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class GetExistsProductImpl implements GetExistsProduct {

    private ProductRepositoryPort productRepository;

    @Override
    public Boolean execute(String code) {
        return productRepository.existsByCode(code);
    }
}
