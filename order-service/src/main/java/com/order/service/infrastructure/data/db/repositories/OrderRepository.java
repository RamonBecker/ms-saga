package com.order.service.infrastructure.data.db.repositories;

import com.order.service.infrastructure.data.db.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {


}
