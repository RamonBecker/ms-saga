package com.product.validation.service.core.usecases.product.impl;


import com.product.validation.service.core.ports.ProductRepositoryPort;
import com.product.validation.service.core.usecases.product.GetExistsProductByCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class GetExistsProductByCodeImpl implements GetExistsProductByCode {

    private ProductRepositoryPort productRepository;

    @Override
    public Boolean execute(String code) {
        return productRepository.existsByCode(code);
    }
}
