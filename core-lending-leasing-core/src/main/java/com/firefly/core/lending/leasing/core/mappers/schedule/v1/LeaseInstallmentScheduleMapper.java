package com.firefly.core.lending.leasing.core.mappers.schedule.v1;

import com.firefly.core.lending.leasing.interfaces.dtos.schedule.v1.LeaseInstallmentScheduleDTO;
import com.firefly.core.lending.leasing.models.entities.schedule.v1.LeaseInstallmentSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaseInstallmentScheduleMapper {
    LeaseInstallmentScheduleDTO toDTO(LeaseInstallmentSchedule entity);
    LeaseInstallmentSchedule toEntity(LeaseInstallmentScheduleDTO dto);
}
