package com.example.orchestrator.service.infrastructure.saga;

import com.example.orchestrator.service.core.usecases.GetNextTopic;
import com.example.orchestrator.service.infrastructure.dto.event.EventDTO;
import com.example.orchestrator.service.infrastructure.shared.constants.Topic;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TopicResolver {

    private final GetNextTopic getNextTopic;

    public TopicResolver(GetNextTopic getNextTopic) {
        this.getNextTopic = getNextTopic;
    }

    public Topic nextTopic(EventDTO event) {
        return getNextTopic.execute(event);
    }
}
