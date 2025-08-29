package com.firefly.core.lending.leasing.interfaces.dtos.record.v1;

import com.firefly.core.utils.annotations.FilterableId;
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
public class LeasePaymentRecordDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long leasePaymentRecordId;

    @FilterableId
    private Long leasingAgreementId;

    @FilterableId
    private Long leaseInstallmentScheduleId;

    private Long transactionId;
    private BigDecimal paymentAmount;
    private LocalDate paymentDate;
    private Boolean isPartialPayment;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

