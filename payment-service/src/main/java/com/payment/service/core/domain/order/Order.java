package com.payment.service.core.domain.order;


import com.payment.service.core.domain.payment.Payment;
import com.payment.service.infrastructure.dto.order.OrderDTO;
import com.payment.service.infrastructure.dto.order.OrderProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.payment.service.infrastructure.shared.constants.ValueOperationCalculation.REDUCE_SUM;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String id;
    private List<OrderProduct> products;
    private LocalDateTime createdAt;
    private String transactionId;
    private double totalAmount;
    private int totalItems;


    public void setTotalItems(Payment payment) {
        this.totalAmount = payment.getTotalAmount();
        this.totalItems = payment.getTotalItems();
    }

    public double calculateAmount() {
        return getProducts().stream().map(orderProduct -> {
            return orderProduct.getProduct().getUnitValue() * orderProduct.getQuantity();
        }).reduce(REDUCE_SUM.getValue(), Double::sum);
    }

    public int calculateTotalItems() {
        return getProducts().stream().map(OrderProduct::getQuantity).reduce(REDUCE_SUM.getValue().intValue(), Integer::sum);
    }

    public static Order fromDomain(OrderDTO dto) {
        return Order.builder()
                .id(dto.getId())
                .products(Order.toDomainOrdersProducts(dto.getProducts()))
                .createdAt(dto.getCreatedAt())
                .transactionId(dto.getTransactionId())
                .totalAmount(dto.getTotalAmount())
                .totalItems(dto.getTotalItems())
                .build();
    }

    public static List<OrderProduct> toDomainOrdersProducts(List<OrderProductDTO> products) {
        if (products == null) return new ArrayList<>();

        return products.stream().map(OrderProduct::fromDomain).toList();
    }
}
