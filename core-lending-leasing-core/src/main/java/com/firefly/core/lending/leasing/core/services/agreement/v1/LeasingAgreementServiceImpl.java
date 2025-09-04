/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.leasing.core.services.agreement.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.core.mappers.agreement.v1.LeasingAgreementMapper;
import com.firefly.core.lending.leasing.interfaces.dtos.agreement.v1.LeasingAgreementDTO;
import com.firefly.core.lending.leasing.models.entities.agreement.v1.LeasingAgreement;
import com.firefly.core.lending.leasing.models.repositories.agreement.v1.LeasingAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

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
    public Mono<LeasingAgreementDTO> getById(UUID leasingAgreementId) {
        return repository.findById(leasingAgreementId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Leasing Agreement not found")));
    }

    @Override
    public Mono<LeasingAgreementDTO> update(UUID leasingAgreementId, LeasingAgreementDTO dto) {
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
    public Mono<Void> delete(UUID leasingAgreementId) {
        return repository.findById(leasingAgreementId)
                .switchIfEmpty(Mono.error(new RuntimeException("Leasing Agreement not found")))
                .flatMap(repository::delete);
    }
}