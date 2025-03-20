package com.order.service.infrastructure.data.db.converters;


//import org.mapstruct.Mapper;

import com.order.service.core.domain.Order;
import com.order.service.core.domain.OrderProduct;
import com.order.service.infrastructure.data.db.entities.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

//@Mapper(componentModel = "spring")
public class OrderConverter {

//    Order orderEntityToOrder(OrderEntity entity);
//
//    OrderEntity orderToOrderEntity(Order order);
//
//    Order orderResponseToOrder(OrderResponse response);
//
//    OrderResponse orderToOrderResponse(Order order);

    public static Order toOrder(OrderEntity entity) {

//        private String id;
//        private List<OrderProduct> products;
//        private LocalDateTime createdAt;
//        private String transactionId;
//        private double totalAmount;
//        private int totalItems;


        return Order.builder().id(entity.getId())
                .products(null)
                .createdAt(entity.getCreatedAt())
                .transactionId(entity.getTransactionId())
                .totalAmount(entity.getTotalAmount())
                .totalItems(entity.getTotalItems()).build();


    }

}
