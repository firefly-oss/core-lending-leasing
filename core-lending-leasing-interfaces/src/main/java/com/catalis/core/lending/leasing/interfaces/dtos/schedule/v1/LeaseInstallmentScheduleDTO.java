package com.catalis.core.lending.leasing.interfaces.dtos.schedule.v1;

import com.catalis.core.utils.annotations.FilterableId;
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
public class LeaseInstallmentScheduleDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long leaseInstallmentScheduleId;

    @FilterableId
    private Long leasingAgreementId;

    private Integer installmentNumber;
    private LocalDate dueDate;
    private BigDecimal principalDue;
    private BigDecimal interestDue;
    private BigDecimal feeDue;
    private BigDecimal totalDue;
    private Boolean isPaid;
    private LocalDate paidDate;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}