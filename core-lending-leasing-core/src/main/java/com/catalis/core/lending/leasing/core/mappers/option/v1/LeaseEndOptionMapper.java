package com.catalis.core.lending.leasing.core.mappers.option.v1;

import com.catalis.core.lending.leasing.interfaces.dtos.option.v1.LeaseEndOptionDTO;
import com.catalis.core.lending.leasing.models.entities.option.v1.LeaseEndOption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaseEndOptionMapper {
    LeaseEndOptionDTO toDTO(LeaseEndOption entity);
    LeaseEndOption toEntity(LeaseEndOptionDTO dto);
}