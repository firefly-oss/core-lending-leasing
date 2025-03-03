package com.catalis.core.lending.leasing.core.services.agreement.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.leasing.core.mappers.agreement.v1.LeasingAgreementMapper;
import com.catalis.core.lending.leasing.interfaces.dtos.agreement.v1.LeasingAgreementDTO;
import com.catalis.core.lending.leasing.models.entities.agreement.v1.LeasingAgreement;
import com.catalis.core.lending.leasing.models.repositories.agreement.v1.LeasingAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class LeasingAgreementServiceImpl implements LeasingAgreementService {

    @Autowired
    private LeasingAgreementRepository repository;

    @Autowired
    private LeasingAgreementMapper mapper;

    @Override
    public Mono<PaginationResponse<LeasingAgreementDTO>> findAll(FilterRequest<LeasingAgreementDTO> filterRequest) {
        return FilterUtils.createFilter(
                LeasingAgreement.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<LeasingAgreementDTO> create(LeasingAgreementDTO dto) {
        LeasingAgreement entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .map(e -> {
                    e.setCreatedAt(LocalDateTime.now());
                    e.setUpdatedAt(LocalDateTime.now());
                    return e;
                })
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeasingAgreementDTO> getById(Long leasingAgreementId) {
        return repository.findById(leasingAgreementId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Leasing Agreement not found")));
    }

    @Override
    public Mono<LeasingAgreementDTO> update(Long leasingAgreementId, LeasingAgreementDTO dto) {
        return repository.findById(leasingAgreementId)
                .switchIfEmpty(Mono.error(new RuntimeException("Leasing Agreement not found")))
                .flatMap(existing -> {
                    LeasingAgreement updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setLeasingAgreementId(existing.getLeasingAgreementId());
                    updatedEntity.setCreatedAt(existing.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long leasingAgreementId) {
        return repository.findById(leasingAgreementId)
                .switchIfEmpty(Mono.error(new RuntimeException("Leasing Agreement not found")))
                .flatMap(repository::delete);
    }
}