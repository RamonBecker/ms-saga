package com.product.validation.service.infrastructure.dto.order;


import com.product.validation.service.infrastructure.dto.product.ProductDTO;
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
