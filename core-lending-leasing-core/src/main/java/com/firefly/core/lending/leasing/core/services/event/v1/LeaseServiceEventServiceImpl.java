package com.firefly.core.lending.leasing.core.services.event.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.core.mappers.event.v1.LeaseServiceEventMapper;
import com.firefly.core.lending.leasing.interfaces.dtos.event.v1.LeaseServiceEventDTO;
import com.firefly.core.lending.leasing.models.entities.event.v1.LeaseServiceEvent;
import com.firefly.core.lending.leasing.models.repositories.event.v1.LeaseServiceEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
@Transactional
public class LeaseServiceEventServiceImpl implements LeaseServiceEventService {

    @Autowired
    private LeaseServiceEventRepository repository;

    @Autowired
    private LeaseServiceEventMapper mapper;

    @Override
    public Mono<PaginationResponse<LeaseServiceEventDTO>> findAll(UUID leasingAgreementId, UUID leasingAssetId, FilterRequest<LeaseServiceEventDTO> filterRequest) {
        filterRequest.getFilters().setLeasingAssetId(leasingAssetId);
        return FilterUtils.createFilter(
                LeaseServiceEvent.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<LeaseServiceEventDTO> create(UUID leasingAgreementId, UUID leasingAssetId, LeaseServiceEventDTO dto) {
        LeaseServiceEvent entity = mapper.toEntity(dto);
        entity.setLeasingAssetId(leasingAssetId);
        return Mono.from(repository.save(entity))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeaseServiceEventDTO> getById(UUID leasingAgreementId, UUID leasingAssetId, UUID leaseServiceEventId) {
        return Mono.from(repository.findById(leaseServiceEventId))
                .filter(event -> event.getLeasingAssetId().equals(leasingAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeaseServiceEventDTO> update(UUID leasingAgreementId, UUID leasingAssetId, UUID leaseServiceEventId, LeaseServiceEventDTO dto) {
        return Mono.from(repository.findById(leaseServiceEventId))
                .filter(event -> event.getLeasingAssetId().equals(leasingAssetId))
                .flatMap(existingEvent -> {
                    LeaseServiceEvent updatedEvent = mapper.toEntity(dto);
                    updatedEvent.setLeaseServiceEventId(existingEvent.getLeaseServiceEventId());
                    updatedEvent.setLeasingAssetId(existingEvent.getLeasingAssetId());
                    updatedEvent.setCreatedAt(existingEvent.getCreatedAt());
                    return Mono.from(repository.save(updatedEvent));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID leasingAgreementId, UUID leasingAssetId, UUID leaseServiceEventId) {
        return Mono.from(repository.findById(leaseServiceEventId))
                .filter(event -> event.getLeasingAssetId().equals(leasingAssetId))
                .flatMap(event -> Mono.from(repository.delete(event)));
    }
}