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


package com.firefly.core.lending.leasing.interfaces.dtos.option.v1;

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
public class LeaseEndOptionDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID leaseEndOptionId;

    @NotNull(message = "Leasing agreement ID is required")
    @FilterableId
    private UUID leasingAgreementId;

    @NotNull(message = "Option exercise date is required")
    @FutureOrPresent(message = "Option exercise date cannot be in the past")
    private LocalDate optionExerciseDate;

    @NotNull(message = "Option paid amount is required")
    @PositiveOrZero(message = "Option paid amount cannot be negative")
    @DecimalMin(value = "0.0", inclusive = true, message = "Option paid amount cannot be negative")
    private BigDecimal optionPaidAmount;

    private Boolean isExercised;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}