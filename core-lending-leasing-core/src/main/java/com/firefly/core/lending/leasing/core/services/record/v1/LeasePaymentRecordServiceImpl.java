package com.firefly.core.lending.leasing.core.services.record.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.core.mappers.record.v1.LeasePaymentRecordMapper;
import com.firefly.core.lending.leasing.interfaces.dtos.record.v1.LeasePaymentRecordDTO;
import com.firefly.core.lending.leasing.models.entities.record.v1.LeasePaymentRecord;
import com.firefly.core.lending.leasing.models.repositories.record.v1.LeasePaymentRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
@Transactional
public class LeasePaymentRecordServiceImpl implements LeasePaymentRecordService {

    @Autowired
    private LeasePaymentRecordRepository repository;

    @Autowired
    private LeasePaymentRecordMapper mapper;

    @Override
    public Mono<PaginationResponse<LeasePaymentRecordDTO>> findAll(UUID leasingAgreementId, FilterRequest<LeasePaymentRecordDTO> filterRequest) {
        filterRequest.getFilters().setLeasingAgreementId(leasingAgreementId);
        return FilterUtils.createFilter(
                LeasePaymentRecord.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<LeasePaymentRecordDTO> create(UUID leasingAgreementId, LeasePaymentRecordDTO dto) {
        dto.setLeasingAgreementId(leasingAgreementId);
        LeasePaymentRecord entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeasePaymentRecordDTO> getById(UUID leasingAgreementId, UUID leasePaymentRecordId) {
        return repository.findById(leasePaymentRecordId)
                .filter(record -> record.getLeasingAgreementId().equals(leasingAgreementId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<LeasePaymentRecordDTO> update(UUID leasingAgreementId, UUID leasePaymentRecordId, LeasePaymentRecordDTO dto) {
        return repository.findById(leasePaymentRecordId)
                .filter(record -> record.getLeasingAgreementId().equals(leasingAgreementId))
                .flatMap(existingRecord -> {
                    dto.setLeasePaymentRecordId(leasePaymentRecordId);
                    dto.setLeasingAgreementId(leasingAgreementId);
                    LeasePaymentRecord updatedEntity = mapper.toEntity(dto);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID leasingAgreementId, UUID leasePaymentRecordId) {
        return repository.findById(leasePaymentRecordId)
                .filter(record -> record.getLeasingAgreementId().equals(leasingAgreementId))
                .flatMap(repository::delete);
    }
}