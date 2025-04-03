package com.product.validation.service.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.product.validation.service.core.usecases.product.GetExistsProduct;
import com.product.validation.service.core.usecases.product.impl.GetExistsProductImpl;
import com.product.validation.service.core.usecases.validation.GetExistsProductValidationByOrderAndTransaction;
import com.product.validation.service.core.usecases.validation.GetProductValidationByOrderAndTransaction;
import com.product.validation.service.core.usecases.validation.SaveProductValidation;
import com.product.validation.service.core.usecases.validation.UpdateProductValidation;
import com.product.validation.service.core.usecases.validation.impl.GetExistsProductValidationByOrderAndTransactionImpl;
import com.product.validation.service.core.usecases.validation.impl.GetProductValidationByOrderAndTransactionImpl;
import com.product.validation.service.core.usecases.validation.impl.SaveProductValidationImpl;
import com.product.validation.service.core.usecases.validation.impl.UpdateProductValidationImpl;
import com.product.validation.service.infrastructure.config.kafka.KafkaProperties;
import com.product.validation.service.infrastructure.data.db.repositories.JpaProductRepository;
import com.product.validation.service.infrastructure.data.db.repositories.JpaProductValidationRepository;
import com.product.validation.service.infrastructure.data.db.repositories.impl.ProductRepository;
import com.product.validation.service.infrastructure.data.db.repositories.impl.ProductValidationRepository;
import com.product.validation.service.infrastructure.rest.api.consumer.ConsumerTopic;
import com.product.validation.service.infrastructure.rest.api.producer.ProducerTopic;
import com.product.validation.service.infrastructure.rest.api.product.SendProduct;
import com.product.validation.service.infrastructure.rest.api.serializers.JsonSerializerImpl;
import com.product.validation.service.infrastructure.shared.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class Module {

    @Autowired
    private JpaProductRepository productRepository;

    @Autowired
    private JpaProductValidationRepository productValidationRepository;

    @Autowired
    private KafkaProperties kafkaProperties;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Bean
    public ObjectMapper createObjectMapper() {

        var objMapper = new ObjectMapper();

        objMapper.registerModule(new JavaTimeModule());

        return objMapper;
    }

    @Bean
    public JsonSerializer createJsonSerializer() {
        return new JsonSerializerImpl(createObjectMapper());
    }

    @Bean
    public ProducerTopic createProducerTopic() {
        return new ProducerTopic(kafkaTemplate, kafkaProperties);
    }

    @Bean
    public ConsumerTopic createConsumerTopic() {
        return new ConsumerTopic(createJsonSerializer(), createSendProduct());
    }

    @Bean
    public ProductRepository createProductRepository() {
        return new ProductRepository(productRepository);
    }

    @Bean
    public ProductValidationRepository createProductValidationRepository() {
        return new ProductValidationRepository(productValidationRepository, createJsonSerializer(), createProducerTopic());
    }

    @Bean
    public SendProduct createSendProduct() {
        return new SendProduct(createSaveProductValidation(), createUpdateProductValidation(), createGetExistsProductValidationByOrderAndTransaction(), createJsonSerializer(), createProducerTopic());
    }

    // use cases
    @Bean
    public GetExistsProduct createGetExistsProduct() {
        return new GetExistsProductImpl(createProductRepository());
    }

    @Bean
    public SaveProductValidation createSaveProductValidation() {
        return new SaveProductValidationImpl(createProductValidationRepository(), createProductRepository());
    }

    @Bean
    public UpdateProductValidation createUpdateProductValidation() {
        return new UpdateProductValidationImpl(createProductValidationRepository());
    }

    @Bean
    public GetExistsProductValidationByOrderAndTransaction createGetExistsProductValidationByOrderAndTransaction() {
        return new GetExistsProductValidationByOrderAndTransactionImpl(createProductValidationRepository());
    }

    @Bean
    public GetProductValidationByOrderAndTransaction createGetProductValidationByOrderAndTransaction() {
        return new GetProductValidationByOrderAndTransactionImpl(createProductValidationRepository());
    }

}
