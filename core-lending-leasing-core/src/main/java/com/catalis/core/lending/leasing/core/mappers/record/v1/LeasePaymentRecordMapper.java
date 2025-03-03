package com.catalis.core.lending.leasing.core.mappers.record.v1;

import com.catalis.core.lending.leasing.interfaces.dtos.record.v1.LeasePaymentRecordDTO;
import com.catalis.core.lending.leasing.models.entities.record.v1.LeasePaymentRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeasePaymentRecordMapper {
    LeasePaymentRecordDTO toDTO(LeasePaymentRecord entity);
    LeasePaymentRecord toEntity(LeasePaymentRecordDTO dto);
}
