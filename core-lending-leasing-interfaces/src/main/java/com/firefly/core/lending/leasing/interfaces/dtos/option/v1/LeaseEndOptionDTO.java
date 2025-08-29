package com.firefly.core.lending.leasing.interfaces.dtos.option.v1;

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
public class LeaseEndOptionDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long leaseEndOptionId;

    @FilterableId
    private Long leasingAgreementId;

    private LocalDate optionExerciseDate;
    private BigDecimal optionPaidAmount;
    private Boolean isExercised;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}