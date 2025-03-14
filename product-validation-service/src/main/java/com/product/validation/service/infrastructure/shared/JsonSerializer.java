package com.product.validation.service.infrastructure.shared;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonSerializer {

    String toJson(Object source) throws JsonProcessingException;
    <T> T fromJson(String json, Class<T> source) throws JsonProcessingException;

}
