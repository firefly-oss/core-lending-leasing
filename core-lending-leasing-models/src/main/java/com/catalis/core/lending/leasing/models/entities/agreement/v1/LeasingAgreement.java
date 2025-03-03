package com.catalis.core.lending.leasing.models.entities.agreement.v1;

import com.catalis.core.lending.leasing.interfaces.enums.agreement.v1.AgreementStatusEnum;
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
@Table("leasing_agreement")
public class LeasingAgreement {

    @Id
    @Column("leasing_agreement_id")
    private Long leasingAgreementId;

    @Column("contract_id")
    private Long contractId;  // external reference to Contract domain

    @Column("customer_id")
    private Long customerId;  // external reference to Customer domain

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