package com.firefly.core.lending.leasing.models.entities.assets.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("leasing_asset")
public class LeasingAsset {

    @Id
    @Column("leasing_asset_id")
    private UUID leasingAssetId;

    @Column("leasing_agreement_id")
    private UUID leasingAgreementId; // FK to LeasingAgreement

    @Column("asset_type_id")
    private UUID assetTypeId;

    @Column("asset_description")
    private String assetDescription;

    @Column("asset_serial_number")
    private String assetSerialNumber;

    @Column("asset_value")
    private BigDecimal assetValue;

    @Column("is_active")
    private Boolean isActive;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
