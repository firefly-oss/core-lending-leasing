package com.firefly.core.lending.leasing.interfaces.dtos.record.v1;

import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeasePaymentRecordDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID leasePaymentRecordId;

    @NotNull(message = "Leasing agreement ID is required")
    @FilterableId
    private UUID leasingAgreementId;

    @NotNull(message = "Lease installment schedule ID is required")
    @FilterableId
    private UUID leaseInstallmentScheduleId;

    @NotNull(message = "Transaction ID is required")
    private UUID transactionId;

    @NotNull(message = "Payment amount is required")
    @Positive(message = "Payment amount must be positive")
    @DecimalMin(value = "0.01", message = "Payment amount must be greater than 0")
    private BigDecimal paymentAmount;

    @NotNull(message = "Payment date is required")
    @PastOrPresent(message = "Payment date cannot be in the future")
    private LocalDate paymentDate;

    private Boolean isPartialPayment;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

