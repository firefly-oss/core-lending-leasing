package com.firefly.core.lending.leasing.interfaces.dtos.event.v1;

import com.firefly.core.lending.leasing.interfaces.enums.event.v1.EventTypeEnum;
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
public class LeaseServiceEventDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long leaseServiceEventId;

    @FilterableId
    private Long leasingAssetId;

    private LocalDate eventDate;
    private EventTypeEnum eventType;
    private BigDecimal cost;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}