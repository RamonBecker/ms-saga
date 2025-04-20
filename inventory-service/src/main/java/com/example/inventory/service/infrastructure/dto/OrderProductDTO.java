package com.example.inventory.service.infrastructure.dto;


import com.example.inventory.service.core.domain.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDTO {

    private ProductDTO product;
    private int quantity;


}
