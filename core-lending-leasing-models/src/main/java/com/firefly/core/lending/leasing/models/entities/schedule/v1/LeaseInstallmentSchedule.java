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


package com.firefly.core.lending.leasing.models.entities.schedule.v1;

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
@Table("lease_installment_schedule")
public class LeaseInstallmentSchedule {

    @Id
    @Column("lease_installment_schedule_id")
    private UUID leaseInstallmentScheduleId;

    @Column("leasing_agreement_id")
    private UUID leasingAgreementId; // FK to LeasingAgreement

    @Column("installment_number")
    private Integer installmentNumber;

    @Column("due_date")
    private LocalDate dueDate;

    @Column("principal_due")
    private BigDecimal principalDue;

    @Column("interest_due")
    private BigDecimal interestDue;

    @Column("fee_due")
    private BigDecimal feeDue;   // optional additional fees

    @Column("total_due")
    private BigDecimal totalDue;

    @Column("is_paid")
    private Boolean isPaid;

    @Column("paid_date")
    private LocalDate paidDate;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
