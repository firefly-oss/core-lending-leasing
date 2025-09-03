package com.firefly.core.lending.leasing.models.repositories.option.v1;

import com.firefly.core.lending.leasing.models.entities.option.v1.LeaseEndOption;
import com.firefly.core.lending.leasing.models.repositories.BaseRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface LeaseEndOptionRepository extends BaseRepository<LeaseEndOption, UUID> {
    Mono<LeaseEndOption> findByLeasingAgreementId(UUID leasingAgreementId);
}