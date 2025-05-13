package com.payment.service.infrastructure.data.db.entities;


import com.payment.service.core.domain.payment.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.payment.service.infrastructure.shared.constants.Status.PENDING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
@Builder
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int totalItems;

    @Column(nullable = false)
    private double totalAmount;

    private String status;

    @PrePersist
    public void prePersist() {
        createdAt = getDateTimeNow();
        updatedAt = getDateTimeNow();
        status = String.valueOf(PENDING);

    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = getDateTimeNow();
    }

    private LocalDateTime getDateTimeNow() {
        return LocalDateTime.now();
    }

    public static PaymentEntity fromEntity(Payment payment) {
        return PaymentEntity.builder().
                id(payment.getId()).
                orderId(payment.getOrderId()).
                createdAt(payment.getCreatedAt()).
                updatedAt(payment.getUpdatedAt()).
                transactionId(payment.getTransactionId()).
                totalItems(payment.getTotalItems()).
                totalAmount(payment.getTotalAmount()).
                status(payment.getStatus()).
                build();
    }

}
