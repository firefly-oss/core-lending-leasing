package com.firefly.core.lending.leasing.models.entities.record.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("lease_payment_record")
public class LeasePaymentRecord {

    @Id
    @Column("lease_payment_record_id")
    private UUID leasePaymentRecordId;

    @Column("leasing_agreement_id")
    private UUID leasingAgreementId; // FK to LeasingAgreement

    @Column("lease_installment_schedule_id")
    private UUID leaseInstallmentScheduleId; // Optional direct link to the schedule

    @Column("transaction_id")
    private UUID transactionId; // external Payment domain reference

    @Column("payment_amount")
    private BigDecimal paymentAmount;

    @Column("payment_date")
    private LocalDate paymentDate;

    @Column("is_partial_payment")
    private Boolean isPartialPayment;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
