package com.firefly.core.lending.leasing.interfaces.dtos.event.v1;

import com.firefly.core.lending.leasing.interfaces.enums.event.v1.EventTypeEnum;
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
public class LeaseServiceEventDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID leaseServiceEventId;

    @NotNull(message = "Leasing asset ID is required")
    @FilterableId
    private UUID leasingAssetId;

    @NotNull(message = "Event date is required")
    @PastOrPresent(message = "Event date cannot be in the future")
    private LocalDate eventDate;

    @NotNull(message = "Event type is required")
    private EventTypeEnum eventType;

    @NotNull(message = "Cost is required")
    @PositiveOrZero(message = "Cost cannot be negative")
    @DecimalMin(value = "0.0", inclusive = true, message = "Cost cannot be negative")
    private BigDecimal cost;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}