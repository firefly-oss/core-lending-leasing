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


package com.firefly.core.lending.leasing.core.services.option.v1;

import com.firefly.core.lending.leasing.core.mappers.option.v1.LeaseEndOptionMapper;
import com.firefly.core.lending.leasing.interfaces.dtos.option.v1.LeaseEndOptionDTO;
import com.firefly.core.lending.leasing.models.repositories.option.v1.LeaseEndOptionRepository;
import com.firefly.core.lending.leasing.models.entities.option.v1.LeaseEndOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
@Transactional
public class LeaseEndOptionServiceImpl implements LeaseEndOptionService {

    @Autowired
    private LeaseEndOptionRepository repository;

    @Autowired
    private LeaseEndOptionMapper mapper;

    @Override
    public Mono<LeaseEndOptionDTO> getByAgreement(UUID leasingAgreementId) {
        return repository.findByLeasingAgreementId(leasingAgreementId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeaseEndOptionDTO> create(UUID leasingAgreementId, LeaseEndOptionDTO dto) {
        LeaseEndOption entity = mapper.toEntity(dto);
        entity.setLeasingAgreementId(leasingAgreementId);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeaseEndOptionDTO> update(UUID leasingAgreementId, LeaseEndOptionDTO dto) {
        return repository.findByLeasingAgreementId(leasingAgreementId)
                .flatMap(existingEntity -> {
                    LeaseEndOption updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setLeaseEndOptionId(existingEntity.getLeaseEndOptionId());
                    updatedEntity.setLeasingAgreementId(leasingAgreementId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID leasingAgreementId) {
        return repository.findByLeasingAgreementId(leasingAgreementId)
                .flatMap(repository::delete);
    }
}