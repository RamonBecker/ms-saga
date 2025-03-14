package com.order.service.infrastructure.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.service.infrastructure.shared.JsonSerializer;

public class EventSerializer implements JsonSerializer {

    private final ObjectMapper objectMapper;

    public EventSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String toJson(Object source) throws JsonProcessingException {
        return objectMapper.writeValueAsString(source);
    }

    @Override
    public <T> T fromJson(String json, Class<T> source) throws JsonProcessingException {
        return objectMapper.readValue(json, source);
    }
}
