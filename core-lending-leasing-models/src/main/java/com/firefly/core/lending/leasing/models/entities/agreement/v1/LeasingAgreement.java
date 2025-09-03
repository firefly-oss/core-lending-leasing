package com.firefly.core.lending.leasing.models.entities.agreement.v1;

import com.firefly.core.lending.leasing.interfaces.enums.agreement.v1.AgreementStatusEnum;
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
@Table("leasing_agreement")
public class LeasingAgreement {

    @Id
    @Column("leasing_agreement_id")
    private UUID leasingAgreementId;

    @Column("contract_id")
    private UUID contractId;  // external reference to Contract domain

    @Column("customer_id")
    private UUID customerId;  // external reference to Customer domain

    @Column("agreement_status")
    private AgreementStatusEnum agreementStatus;

    @Column("start_date")
    private LocalDate startDate;

    @Column("end_date")
    private LocalDate endDate;

    @Column("principal_amount")
    private BigDecimal principalAmount;

    @Column("interest_rate")
    private BigDecimal interestRate;

    @Column("residual_value")
    private BigDecimal residualValue;

    @Column("purchase_option")
    private Boolean purchaseOption;

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}