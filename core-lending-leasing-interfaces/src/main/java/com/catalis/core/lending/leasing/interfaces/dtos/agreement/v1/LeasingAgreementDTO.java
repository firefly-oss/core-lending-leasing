package com.catalis.core.lending.leasing.interfaces.dtos.agreement.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.leasing.interfaces.enums.agreement.v1.AgreementStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeasingAgreementDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long leasingAgreementId;

    @FilterableId
    private Long contractId;

    @FilterableId
    private Long customerId;

    private AgreementStatusEnum agreementStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal principalAmount;
    private BigDecimal interestRate;
    private BigDecimal residualValue;
    private Boolean purchaseOption;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}