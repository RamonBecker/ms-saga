package com.payment.service.infrastructure.rest.api.payment;


import com.payment.service.core.domain.event.Event;
import com.payment.service.core.usecases.payment.GetPaymentByOrderAndTransaction;
import com.payment.service.core.usecases.payment.SavePayment;
import com.payment.service.core.usecases.payment.UpdatePaymentStatus;
import com.payment.service.infrastructure.rest.api.publisher.EventPublisher;
import lombok.extern.slf4j.Slf4j;

import static com.payment.service.infrastructure.shared.constants.CommonConstants.CURRENT_SOURCE;
import static com.payment.service.infrastructure.shared.constants.Status.SUCCESS;
import static com.payment.service.infrastructure.shared.constants.Status.ROLLBACK_PENDING;
import static com.payment.service.infrastructure.shared.constants.Status.FAIL;
import static com.payment.service.infrastructure.shared.constants.Status.REFUND;

@Slf4j
public class PaymentResolver {

    private final SavePayment save;
    private final UpdatePaymentStatus update;
    private final GetPaymentByOrderAndTransaction getPayment;
    private final EventPublisher publisher;

    public PaymentResolver(SavePayment save, UpdatePaymentStatus update, GetPaymentByOrderAndTransaction getPayment, EventPublisher publisher) {
        this.save = save;
        this.update = update;
        this.getPayment = getPayment;
        this.publisher = publisher;
    }

    public void success(Event event) throws Exception {

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

        publisher.publish(event);
    }


    public void rollback(Event event) throws Exception {

        event.setStatus(String.valueOf(FAIL));
        event.setSource(CURRENT_SOURCE);

        try {
            update.execute(REFUND, event);
            event.getOrder().setTotalItems(getPayment.execute(event.getOrderId(), event.getTransactionId()));
            event.addHistory("Rollback executed on payment!");

        } catch (Exception e) {
            event.addHistory("Rollback not executed for payment: ".concat(e.getMessage()));
        }

        publisher.publish(event);
    }
}
