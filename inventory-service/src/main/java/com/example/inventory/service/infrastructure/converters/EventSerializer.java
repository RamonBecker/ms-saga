package com.example.inventory.service.infrastructure.converters;

import com.example.inventory.service.infrastructure.shared.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
