package com.example.inventory.service.infrastructure.serializers.impl;

import com.example.inventory.service.infrastructure.serializers.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonSerializerImpl implements JsonSerializer {

    private final ObjectMapper objectMapper;

    public JsonSerializerImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
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
