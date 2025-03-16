package com.order.service.infrastructure.data.db.repositories;

import com.order.service.infrastructure.data.db.entities.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {

    
}
