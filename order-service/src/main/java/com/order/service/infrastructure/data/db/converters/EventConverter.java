package com.order.service.infrastructure.data.db.converters;

import com.order.service.core.domain.Event;
import com.order.service.infrastructure.data.db.entities.EventEntity;
import com.order.service.infrastructure.rest.api.responses.EventResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventConverter {

    Event eventEntityToEvent(EventEntity entity);

    EventResponse eventToEventResponse(Event event);

    EventEntity eventToEventEntity(Event order);

    List<Event> eventEntitiesToEvents(List<EventEntity> events);

    List<EventResponse> eventToEventResponses(List<Event> events);
}
