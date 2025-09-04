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


package com.firefly.core.lending.leasing.interfaces.dtos.agreement.v1;

import com.firefly.core.lending.leasing.interfaces.enums.agreement.v1.AgreementStatusEnum;
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
public class LeasingAgreementDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID leasingAgreementId;

    @NotNull(message = "Contract ID is required")
    @FilterableId
    private UUID contractId;

    @NotNull(message = "Customer ID is required")
    @FilterableId
    private UUID customerId;

    @NotNull(message = "Agreement status is required")
    private AgreementStatusEnum agreementStatus;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @NotNull(message = "Principal amount is required")
    @Positive(message = "Principal amount must be positive")
    @DecimalMin(value = "0.01", message = "Principal amount must be greater than 0")
    private BigDecimal principalAmount;

    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Interest rate cannot be negative")
    @DecimalMax(value = "100.0", message = "Interest rate cannot exceed 100%")
    private BigDecimal interestRate;

    @Positive(message = "Residual value must be positive")
    private BigDecimal residualValue;

    private Boolean purchaseOption;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}