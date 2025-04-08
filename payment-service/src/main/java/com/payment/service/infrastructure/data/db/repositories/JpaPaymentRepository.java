package com.payment.service.infrastructure.data.db.repositories;

import com.payment.service.infrastructure.data.db.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Integer> {

    boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<PaymentEntity> getByOrderIdAndTransactionId(String orderId, String transactionId);
}
