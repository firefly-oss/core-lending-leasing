package com.catalis.core.lending.leasing.core.services.event.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.leasing.core.mappers.event.v1.LeaseServiceEventMapper;
import com.catalis.core.lending.leasing.interfaces.dtos.event.v1.LeaseServiceEventDTO;
import com.catalis.core.lending.leasing.models.entities.event.v1.LeaseServiceEvent;
import com.catalis.core.lending.leasing.models.repositories.event.v1.LeaseServiceEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class LeaseServiceEventServiceImpl implements LeaseServiceEventService {

    @Autowired
    private LeaseServiceEventRepository repository;

    @Autowired
    private LeaseServiceEventMapper mapper;

    @Override
    public Mono<PaginationResponse<LeaseServiceEventDTO>> findAll(Long leasingAgreementId, Long leasingAssetId, FilterRequest<LeaseServiceEventDTO> filterRequest) {
        filterRequest.getFilters().setLeasingAssetId(leasingAssetId);
        return FilterUtils.createFilter(
                LeaseServiceEvent.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<LeaseServiceEventDTO> create(Long leasingAgreementId, Long leasingAssetId, LeaseServiceEventDTO dto) {
        LeaseServiceEvent entity = mapper.toEntity(dto);
        entity.setLeasingAssetId(leasingAssetId);
        return Mono.from(repository.save(entity))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeaseServiceEventDTO> getById(Long leasingAgreementId, Long leasingAssetId, Long leaseServiceEventId) {
        return Mono.from(repository.findById(leaseServiceEventId))
                .filter(event -> event.getLeasingAssetId().equals(leasingAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeaseServiceEventDTO> update(Long leasingAgreementId, Long leasingAssetId, Long leaseServiceEventId, LeaseServiceEventDTO dto) {
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
    public Mono<Void> delete(Long leasingAgreementId, Long leasingAssetId, Long leaseServiceEventId) {
        return Mono.from(repository.findById(leaseServiceEventId))
                .filter(event -> event.getLeasingAssetId().equals(leasingAssetId))
                .flatMap(event -> Mono.from(repository.delete(event)));
    }
}