package com.payment.service.infrastructure.rest.api.payment;


import com.payment.service.core.domain.Event;
import com.payment.service.core.usecases.payment.GetPaymentByOrderAndTransaction;
import com.payment.service.core.usecases.payment.SavePayment;
import com.payment.service.core.usecases.payment.UpdatePaymentStatus;
import com.payment.service.infrastructure.dto.event.EventDTO;
import com.payment.service.infrastructure.rest.api.producer.ProducerTopic;
import com.payment.service.infrastructure.shared.JsonSerializer;
import lombok.extern.slf4j.Slf4j;

import static com.payment.service.infrastructure.shared.constants.CommonConstants.CURRENT_SOURCE;
import static com.payment.service.infrastructure.shared.constants.SagaStatus.SUCCESS;
import static com.payment.service.infrastructure.shared.constants.SagaStatus.ROLLBACK_PENDING;
import static com.payment.service.infrastructure.shared.constants.SagaStatus.FAIL;
import static com.payment.service.infrastructure.shared.constants.SagaStatus.REFUND;

@Slf4j
public class SendPayment {

    private final SavePayment save;
    private final UpdatePaymentStatus update;
    private final GetPaymentByOrderAndTransaction getPayment;
    private final JsonSerializer serializer;
    private final ProducerTopic producer;


    public SendPayment(SavePayment save, UpdatePaymentStatus update, GetPaymentByOrderAndTransaction getPayment, JsonSerializer serializer, ProducerTopic producer) {
        this.save = save;
        this.update = update;
        this.getPayment = getPayment;
        this.serializer = serializer;
        this.producer = producer;
    }

    public void success(EventDTO eventDTO) throws Exception {

        var event = Event.fromDomain(eventDTO);
        event.setSource(CURRENT_SOURCE);

        try {

            save.execute(event);

            event.getOrder().setTotalItems(getPayment.execute(event.getOrderId(), event.getTransactionId()));

            update.execute(SUCCESS, event);

            event.setStatus(String.valueOf(SUCCESS));
            event.addHistory("Payment was successfully realized!");

        } catch (Exception ex) {

            log.error("Error trying to make payment: ", ex);
            event.setStatus(String.valueOf(ROLLBACK_PENDING));
            event.addHistory("Fail to realize payment: ".concat(ex.getMessage()));
        }

        producer.send(serializer.toJson(event));
    }


    public void rollback(EventDTO eventDTO) throws Exception {

        var event = Event.fromDomain(eventDTO);
        event.setStatus(String.valueOf(FAIL));
        event.setSource(CURRENT_SOURCE);

        try {
            update.execute(REFUND, event);
            event.getOrder().setTotalItems(getPayment.execute(event.getOrderId(), event.getTransactionId()));
            event.addHistory("Rollback executed on payment!");

        } catch (Exception e) {
            event.addHistory("Rollback not executed for payment: ".concat(e.getMessage()));
        }

        producer.send(serializer.toJson(event));
    }
}
