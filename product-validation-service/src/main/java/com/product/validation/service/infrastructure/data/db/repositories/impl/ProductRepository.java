package com.product.validation.service.infrastructure.data.db.repositories.impl;

import com.product.validation.service.core.ports.ProductRepositoryPort;
import com.product.validation.service.infrastructure.data.db.repositories.JpaProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ProductRepository implements ProductRepositoryPort {

    private JpaProductRepository repository;

    @Override
    public boolean existsByCode(String code) {
        return repository.existsByCode(code);
    }
}
