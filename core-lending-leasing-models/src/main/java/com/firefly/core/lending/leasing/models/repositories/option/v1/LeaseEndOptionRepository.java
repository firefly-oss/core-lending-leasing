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


package com.firefly.core.lending.leasing.models.repositories.option.v1;

import com.firefly.core.lending.leasing.models.entities.option.v1.LeaseEndOption;
import com.firefly.core.lending.leasing.models.repositories.BaseRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface LeaseEndOptionRepository extends BaseRepository<LeaseEndOption, UUID> {
    Mono<LeaseEndOption> findByLeasingAgreementId(UUID leasingAgreementId);
}