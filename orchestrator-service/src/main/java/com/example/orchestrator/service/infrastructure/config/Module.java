package com.example.orchestrator.service.infrastructure.config;

import com.example.orchestrator.service.core.ports.SagaTransitionRepositoryPort;
import com.example.orchestrator.service.core.usecases.GetNextTopic;
import com.example.orchestrator.service.core.usecases.impl.GetNextTopicImpl;
import com.example.orchestrator.service.infrastructure.config.logs.OrchestratorLogger;
import com.example.orchestrator.service.infrastructure.config.logs.TopicLogger;
import com.example.orchestrator.service.infrastructure.data.db.repository.InMemorySagaTransitionRepository;
import com.example.orchestrator.service.infrastructure.rest.api.producer.ProducerTopic;
import com.example.orchestrator.service.infrastructure.rest.api.publisher.EventPublisher;
import com.example.orchestrator.service.infrastructure.saga.impl.SagaEventHandlerImpl;
import com.example.orchestrator.service.infrastructure.saga.impl.SagaResolverImpl;
import com.example.orchestrator.service.infrastructure.saga.TopicResolver;
import com.example.orchestrator.service.infrastructure.saga.usecases.SagaEventHandler;
import com.example.orchestrator.service.infrastructure.saga.usecases.SagaResolver;
import com.example.orchestrator.service.infrastructure.serializers.impl.JsonSerializerImpl;
import com.example.orchestrator.service.infrastructure.serializers.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module {


    @Autowired
    private ProducerTopic producerTopic;

    @Bean
    public TopicResolver createTopicResolver() {
        return new TopicResolver(createGetNextTopic());
    }


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
    public SagaTransitionRepositoryPort createSagaTransitionRepositoryPort() {
        return new InMemorySagaTransitionRepository();
    }

    @Bean
    public GetNextTopic createGetNextTopic() {
        return new GetNextTopicImpl(createSagaTransitionRepositoryPort());
    }

    @Bean
    public TopicLogger createTopicLogger() {
        return new TopicLogger();
    }

    @Bean
    public OrchestratorLogger createOrchestratorLogger() {
        return new OrchestratorLogger();
    }

    @Bean
    public EventPublisher createEventPublisher() {
        return new EventPublisher(createJsonSerializer(), producerTopic);
    }

    @Bean
    public SagaResolver createSagaResolver() {
        return new SagaResolverImpl(createTopicResolver(), producerTopic, createJsonSerializer(), createTopicLogger(), createOrchestratorLogger(), createEventPublisher());
    }

    @Bean
    public SagaEventHandler createSagaEventHandler() {
        return new SagaEventHandlerImpl(createSagaResolver());
    }
}
