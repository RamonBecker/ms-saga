package com.product.validation.service.infrastructure.data.db.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.product.validation.service.core.domain.event.Event;
import com.product.validation.service.core.domain.product.ProductValidation;
import com.product.validation.service.core.ports.ProductValidationRepositoryPort;
import com.product.validation.service.infrastructure.data.db.entities.ProductValidationEntity;
import com.product.validation.service.infrastructure.data.db.repositories.JpaProductValidationRepository;
import com.product.validation.service.infrastructure.rest.api.producer.ProducerTopic;
import com.product.validation.service.infrastructure.rest.api.serializers.JsonSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ProductValidationRepository implements ProductValidationRepositoryPort {

    private JpaProductValidationRepository validationRepository;
    private JsonSerializer serializer;
    private ProducerTopic producerTopic;

    @Override
    public void send(Event event) throws JsonProcessingException {
        producerTopic.send(serializer.toJson(event));
    }

    @Override
    public void save(ProductValidation productValidation) {

        if (existsByOrderIdAndTransactionId(productValidation.getOrderId(), productValidation.getTransactionId()))
            productValidation.setId(findByOrderIdAndTransactionId(productValidation.getOrderId(), productValidation.getTransactionId()).get().getId());

        validationRepository.save(ProductValidationEntity.fromEntity(productValidation));
    }

    @Override
    public Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId) {
        return validationRepository.existsByOrderIdAndTransactionId(orderId, transactionId);
    }

    @Override
    public Optional<ProductValidation> findByOrderIdAndTransactionId(String orderId, String transactionId) {
        return validationRepository.findByOrderIdAndTransactionId(orderId, transactionId).map(ProductValidation::fromDomain);
    }
}
