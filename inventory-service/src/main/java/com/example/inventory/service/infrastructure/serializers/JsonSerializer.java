package com.example.inventory.service.infrastructure.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonSerializer {

    String toJson(Object source) throws JsonProcessingException;
    <T> T fromJson(String json, Class<T> source) throws JsonProcessingException;

}
