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

import java.time.LocalDateTime;

@Service
@Transactional
public class LeasingAssetServiceImpl implements LeasingAssetService {

    @Autowired
    private LeasingAssetRepository repository;

    @Autowired
    private LeasingAssetMapper mapper;

    @Override
    public Mono<PaginationResponse<LeasingAssetDTO>> findAll(Long leasingAgreementId, FilterRequest<LeasingAssetDTO> filterRequest) {
        filterRequest.getFilters().setLeasingAgreementId(leasingAgreementId);
        return FilterUtils.createFilter(
                LeasingAsset.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<LeasingAssetDTO> create(Long leasingAgreementId, LeasingAssetDTO dto) {
        dto.setLeasingAgreementId(leasingAgreementId);
        LeasingAsset entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .doOnNext(e -> e.setCreatedAt(LocalDateTime.now()))
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeasingAssetDTO> getById(Long leasingAgreementId, Long leasingAssetId) {
        return repository.findById(leasingAssetId)
                .filter(asset -> leasingAgreementId.equals(asset.getLeasingAgreementId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeasingAssetDTO> update(Long leasingAgreementId, Long leasingAssetId, LeasingAssetDTO dto) {
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
    public Mono<Void> delete(Long leasingAgreementId, Long leasingAssetId) {
        return repository.findById(leasingAssetId)
                .filter(asset -> leasingAgreementId.equals(asset.getLeasingAgreementId()))
                .flatMap(repository::delete);
    }
}
