package com.catalis.core.lending.leasing.models.repositories.option.v1;

import com.catalis.core.lending.leasing.models.entities.option.v1.LeaseEndOption;
import com.catalis.core.lending.leasing.models.repositories.BaseRepository;
import reactor.core.publisher.Mono;

public interface LeaseEndOptionRepository extends BaseRepository<LeaseEndOption, Long> {
    Mono<LeaseEndOption> findByLeasingAgreementId(Long leasingAgreementId);
}