package com.catalis.core.lending.leasing.interfaces.dtos.assets.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.leasing.interfaces.enums.assets.v1.AssetTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeasingAssetDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long leasingAssetId;

    @FilterableId
    private Long leasingAgreementId;

    private AssetTypeEnum assetType;
    private String assetDescription;
    private String assetSerialNumber;
    private BigDecimal assetValue;
    private Boolean isActive;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}