package com.firefly.core.lending.leasing.core.mappers.assets.v1;

import com.firefly.core.lending.leasing.interfaces.dtos.assets.v1.LeasingAssetDTO;
import com.firefly.core.lending.leasing.models.entities.assets.v1.LeasingAsset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeasingAssetMapper {
    LeasingAssetDTO toDTO(LeasingAsset entity);
    LeasingAsset toEntity(LeasingAssetDTO dto);
}