package com.order.service.infrastructure.data.db.converters;

import com.order.service.core.domain.Product;
import com.order.service.infrastructure.data.db.entities.ProductEntity;

import java.util.List;

public class ProductConverter {

    public static List<Product> toProducts(List<ProductEntity> products) {
        return products.stream().map(Product::from).toList();
    }

    public static List<ProductEntity> toProductsEntities(List<Product> products) {
        return products.stream().map(ProductEntity::from).toList();
    }

}
