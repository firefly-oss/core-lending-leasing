package com.firefly.core.lending.leasing.interfaces.dtos.schedule.v1;

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
public class LeaseInstallmentScheduleDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID leaseInstallmentScheduleId;

    @NotNull(message = "Leasing agreement ID is required")
    @FilterableId
    private UUID leasingAgreementId;

    @NotNull(message = "Installment number is required")
    @Min(value = 1, message = "Installment number must be at least 1")
    private Integer installmentNumber;

    @NotNull(message = "Due date is required")
    @Future(message = "Due date must be in the future")
    private LocalDate dueDate;

    @NotNull(message = "Principal due is required")
    @PositiveOrZero(message = "Principal due cannot be negative")
    @DecimalMin(value = "0.0", inclusive = true, message = "Principal due cannot be negative")
    private BigDecimal principalDue;

    @NotNull(message = "Interest due is required")
    @PositiveOrZero(message = "Interest due cannot be negative")
    @DecimalMin(value = "0.0", inclusive = true, message = "Interest due cannot be negative")
    private BigDecimal interestDue;

    @PositiveOrZero(message = "Fee due cannot be negative")
    @DecimalMin(value = "0.0", inclusive = true, message = "Fee due cannot be negative")
    private BigDecimal feeDue;

    @NotNull(message = "Total due is required")
    @Positive(message = "Total due must be positive")
    @DecimalMin(value = "0.01", message = "Total due must be greater than 0")
    private BigDecimal totalDue;

    private Boolean isPaid;

    @PastOrPresent(message = "Paid date cannot be in the future")
    private LocalDate paidDate;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}