package com.firefly.core.lending.leasing.models.entities.option.v1;

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
@Table("lease_end_option")
public class LeaseEndOption {

    @Id
    @Column("lease_end_option_id")
    private Long leaseEndOptionId;

    @Column("leasing_agreement_id")
    private Long leasingAgreementId; // references LeasingAgreement

    @Column("option_exercise_date")
    private LocalDate optionExerciseDate;

    @Column("option_paid_amount")
    private BigDecimal optionPaidAmount;

    @Column("is_exercised")
    private Boolean isExercised;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
