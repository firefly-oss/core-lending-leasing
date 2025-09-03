package com.firefly.core.lending.leasing.interfaces.dtos.assets.v1;

import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeasingAssetDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID leasingAssetId;

    @NotNull(message = "Leasing agreement ID is required")
    @FilterableId
    private UUID leasingAgreementId;

    @NotNull(message = "Asset type ID is required")
    @FilterableId
    private UUID assetTypeId;

    @NotBlank(message = "Asset description is required")
    @Size(min = 3, max = 500, message = "Asset description must be between 3 and 500 characters")
    private String assetDescription;

    @NotBlank(message = "Asset serial number is required")
    @Size(min = 1, max = 100, message = "Asset serial number must be between 1 and 100 characters")
    private String assetSerialNumber;

    @NotNull(message = "Asset value is required")
    @Positive(message = "Asset value must be positive")
    @DecimalMin(value = "0.01", message = "Asset value must be greater than 0")
    private BigDecimal assetValue;

    private Boolean isActive;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}