package com.firefly.core.lending.leasing.core.services.schedule.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.core.mappers.schedule.v1.LeaseInstallmentScheduleMapper;
import com.firefly.core.lending.leasing.interfaces.dtos.schedule.v1.LeaseInstallmentScheduleDTO;
import com.firefly.core.lending.leasing.models.entities.schedule.v1.LeaseInstallmentSchedule;
import com.firefly.core.lending.leasing.models.repositories.schedule.v1.LeaseInstallmentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.time.LocalDateTime;

@Service
@Transactional
public class LeaseInstallmentScheduleServiceImpl implements LeaseInstallmentScheduleService {

    @Autowired
    private LeaseInstallmentScheduleRepository repository;

    @Autowired
    private LeaseInstallmentScheduleMapper mapper;

    @Override
    public Mono<PaginationResponse<LeaseInstallmentScheduleDTO>> findAll(UUID leasingAgreementId, FilterRequest<LeaseInstallmentScheduleDTO> filterRequest) {
        filterRequest.getFilters().setLeasingAgreementId(leasingAgreementId);
        return FilterUtils.createFilter(
                LeaseInstallmentSchedule.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<LeaseInstallmentScheduleDTO> create(UUID leasingAgreementId, LeaseInstallmentScheduleDTO dto) {
        dto.setLeasingAgreementId(leasingAgreementId);
        LeaseInstallmentSchedule entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeaseInstallmentScheduleDTO> getById(UUID leasingAgreementId, UUID leaseInstallmentScheduleId) {
        return repository.findById(leaseInstallmentScheduleId)
                .filter(entity -> entity.getLeasingAgreementId().equals(leasingAgreementId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeaseInstallmentScheduleDTO> update(UUID leasingAgreementId, UUID leaseInstallmentScheduleId, LeaseInstallmentScheduleDTO dto) {
        return repository.findById(leaseInstallmentScheduleId)
                .filter(entity -> entity.getLeasingAgreementId().equals(leasingAgreementId))
                .flatMap(existingEntity -> {
                    dto.setLeaseInstallmentScheduleId(leaseInstallmentScheduleId);
                    dto.setLeasingAgreementId(leasingAgreementId);
                    LeaseInstallmentSchedule entity = mapper.toEntity(dto);
                    entity.setUpdatedAt(LocalDateTime.now());
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID leasingAgreementId, UUID leaseInstallmentScheduleId) {
        return repository.findById(leaseInstallmentScheduleId)
                .filter(entity -> entity.getLeasingAgreementId().equals(leasingAgreementId))
                .flatMap(repository::delete);
    }
}