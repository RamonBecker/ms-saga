package com.example.inventory.service.core.ports;

import com.example.inventory.service.core.domain.inventory.OrderInventory;

import java.util.List;

public interface OrderInventoryRepositoryPort {

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    void save(OrderInventory order);

    List<OrderInventory> findByOrderIdAndTransactionId(String orderId, String transactionId);
}
