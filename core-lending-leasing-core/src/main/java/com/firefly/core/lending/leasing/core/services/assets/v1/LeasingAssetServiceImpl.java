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


package com.firefly.core.lending.leasing.core.services.assets.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.core.mappers.assets.v1.LeasingAssetMapper;
import com.firefly.core.lending.leasing.interfaces.dtos.assets.v1.LeasingAssetDTO;
import com.firefly.core.lending.leasing.models.entities.assets.v1.LeasingAsset;
import com.firefly.core.lending.leasing.models.repositories.assets.v1.LeasingAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.time.LocalDateTime;

@Service
@Transactional
public class LeasingAssetServiceImpl implements LeasingAssetService {

    @Autowired
    private LeasingAssetRepository repository;

    @Autowired
    private LeasingAssetMapper mapper;

    @Override
    public Mono<PaginationResponse<LeasingAssetDTO>> findAll(UUID leasingAgreementId, FilterRequest<LeasingAssetDTO> filterRequest) {
        filterRequest.getFilters().setLeasingAgreementId(leasingAgreementId);
        return FilterUtils.createFilter(
                LeasingAsset.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<LeasingAssetDTO> create(UUID leasingAgreementId, LeasingAssetDTO dto) {
        dto.setLeasingAgreementId(leasingAgreementId);
        LeasingAsset entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .doOnNext(e -> e.setCreatedAt(LocalDateTime.now()))
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeasingAssetDTO> getById(UUID leasingAgreementId, UUID leasingAssetId) {
        return repository.findById(leasingAssetId)
                .filter(asset -> leasingAgreementId.equals(asset.getLeasingAgreementId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeasingAssetDTO> update(UUID leasingAgreementId, UUID leasingAssetId, LeasingAssetDTO dto) {
        return repository.findById(leasingAssetId)
                .filter(asset -> leasingAgreementId.equals(asset.getLeasingAgreementId()))
                .flatMap(existingAsset -> {
                    LeasingAsset updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setLeasingAssetId(existingAsset.getLeasingAssetId());
                    updatedEntity.setLeasingAgreementId(existingAsset.getLeasingAgreementId());
                    updatedEntity.setCreatedAt(existingAsset.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID leasingAgreementId, UUID leasingAssetId) {
        return repository.findById(leasingAssetId)
                .filter(asset -> leasingAgreementId.equals(asset.getLeasingAgreementId()))
                .flatMap(repository::delete);
    }
}
