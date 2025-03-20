package com.order.service.infrastructure.data.db.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductEntity {

    private ProductEntity product;
    private int quantity;

}
