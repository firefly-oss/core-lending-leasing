package com.catalis.core.lending.leasing.core.mappers.event.v1;

import com.catalis.core.lending.leasing.interfaces.dtos.event.v1.LeaseServiceEventDTO;
import com.catalis.core.lending.leasing.models.entities.event.v1.LeaseServiceEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaseServiceEventMapper {
    LeaseServiceEventDTO toDTO(LeaseServiceEvent entity);
    LeaseServiceEvent toEntity(LeaseServiceEventDTO dto);
}
