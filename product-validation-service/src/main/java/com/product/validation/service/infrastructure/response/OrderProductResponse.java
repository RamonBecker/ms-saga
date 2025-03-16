package com.product.validation.service.infrastructure.response;


import com.order.service.infrastructure.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductResponse {

    private ProductResponse product;
    private int quantity;

}
