package com.catalis.core.lending.leasing.models.entities.schedule.v1;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("lease_installment_schedule")
public class LeaseInstallmentSchedule {

    @Id
    @Column("lease_installment_schedule_id")
    private Long leaseInstallmentScheduleId;

    @Column("leasing_agreement_id")
    private Long leasingAgreementId; // FK to LeasingAgreement

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
