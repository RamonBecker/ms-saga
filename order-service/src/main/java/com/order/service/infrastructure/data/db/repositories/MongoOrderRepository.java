package com.order.service.infrastructure.data.db.repositories;

import com.order.service.infrastructure.data.db.entities.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOrderRepository extends MongoRepository<OrderEntity, String> {


}
