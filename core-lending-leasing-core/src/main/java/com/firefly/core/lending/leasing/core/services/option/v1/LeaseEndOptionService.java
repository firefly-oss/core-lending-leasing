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

import com.firefly.core.lending.leasing.interfaces.dtos.option.v1.LeaseEndOptionDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface LeaseEndOptionService {

    /**
     * Retrieves the lease end option details associated with a specific leasing agreement ID.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement whose lease end option details are to be retrieved
     * @return a Mono emitting the LeaseEndOptionDTO containing the lease end option details, or empty if not found
     */
    Mono<LeaseEndOptionDTO> getByAgreement(UUID leasingAgreementId);

    /**
     * Creates a new lease end option associated with the specified leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement to which the lease end option is associated
     * @param dto the data transfer object containing the details of the lease end option to be created
     * @return a Mono emitting the created LeaseEndOptionDTO object upon successful creation
     */
    Mono<LeaseEndOptionDTO> create(UUID leasingAgreementId, LeaseEndOptionDTO dto);

    /**
     * Updates an existing lease end option associated with a specific leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement associated with the lease end option
     * @param dto the data transfer object containing updated details of the lease end option
     * @return a Mono emitting the updated LeaseEndOptionDTO upon successful update
     */
    Mono<LeaseEndOptionDTO> update(UUID leasingAgreementId, LeaseEndOptionDTO dto);

    /**
     * Deletes the lease end option associated with the specified leasing agreement ID.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement whose lease end option is to be deleted
     * @return a Mono signaling the completion of the deletion process
     */
    Mono<Void> delete(UUID leasingAgreementId);
}
