package com.catalis.core.lending.leasing.core.mappers.agreement.v1;

import com.catalis.core.lending.leasing.interfaces.dtos.agreement.v1.LeasingAgreementDTO;
import com.catalis.core.lending.leasing.models.entities.agreement.v1.LeasingAgreement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeasingAgreementMapper {
    LeasingAgreementDTO toDTO(LeasingAgreement entity);
    LeasingAgreement toEntity(LeasingAgreementDTO dto);
}
