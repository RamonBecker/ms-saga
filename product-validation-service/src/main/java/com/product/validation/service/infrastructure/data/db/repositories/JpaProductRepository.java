package com.product.validation.service.infrastructure.data.db.repositories;

import com.product.validation.service.infrastructure.data.db.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Integer> {

    Boolean existsByCode(String code);
}
