/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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
