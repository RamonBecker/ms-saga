package com.example.orchestrator.service.infrastructure.config.logs;


import com.example.orchestrator.service.infrastructure.dto.event.EventDTO;
import com.example.orchestrator.service.infrastructure.shared.constants.Topic;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrchestratorLogger {

    public void log(EventDTO event, Topic topic){

        switch (topic){
            case START_SAGA -> log.info("START SAGA");

            case CONTINUE_SAGA -> log.info("SAGA CONTINUING FOR EVENT {}", event.getId());

            case FINISH_FAIL -> log.info("SAGA FINISHED WITH ERRORS FOR EVENT {}!", event.getId());

            case FINISH_SUCCESS -> log.info("SAGA FINISHED SUCCESSFULLY");
        }
    }
}
