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


package com.firefly.core.lending.leasing.core.services.event.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.interfaces.dtos.event.v1.LeaseServiceEventDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface LeaseServiceEventService {

    /**
     * Retrieves a paginated list of Lease Service Events based on the specified filtering criteria and
     * associated with the given Leasing Agreement ID and Leasing Asset ID.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement to which the events belong
     * @param leasingAssetId the unique identifier of the leasing asset to which the events are related
     * @param filterRequest the filtering and pagination criteria used to retrieve the lease service events
     * @return a Mono emitting a PaginationResponse containing a list of LeaseServiceEventDTO objects
     *         that match the given criteria
     */
    Mono<PaginationResponse<LeaseServiceEventDTO>> findAll(UUID leasingAgreementId,
                                                           UUID leasingAssetId,
                                                           FilterRequest<LeaseServiceEventDTO> filterRequest);

    /**
     * Creates a new lease service event for a specific leasing agreement and leasing asset.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement associated with the lease service event
     * @param leasingAssetId the unique identifier of the leasing asset associated with the lease service event
     * @param dto the data transfer object containing the details of the lease service event to be created
     * @return a Mono emitting the created LeaseServiceEventDTO object upon successful creation
     */
    Mono<LeaseServiceEventDTO> create(UUID leasingAgreementId,
                                      UUID leasingAssetId,
                                      LeaseServiceEventDTO dto);

    /**
     * Retrieves a specific Lease Service Event associated with the given leasing agreement ID,
     * leasing asset ID, and lease service event ID.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement associated with the event
     * @param leasingAssetId the unique identifier of the leasing asset associated with the event
     * @param leaseServiceEventId the unique identifier of the lease service event to retrieve
     * @return a Mono emitting the LeaseServiceEventDTO corresponding to the provided IDs, or empty if not found
     */
    Mono<LeaseServiceEventDTO> getById(UUID leasingAgreementId,
                                       UUID leasingAssetId,
                                       UUID leaseServiceEventId);

    /**
     * Updates an existing lease service event associated with the specified leasing agreement and asset.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement associated with the lease service event
     * @param leasingAssetId the unique identifier of the leasing asset associated with the lease service event
     * @param leaseServiceEventId the unique identifier of the lease service event to be updated
     * @param dto the data transfer object containing the updated details for the lease service event
     * @return a Mono emitting the updated LeaseServiceEventDTO object upon successful update
     */
    Mono<LeaseServiceEventDTO> update(UUID leasingAgreementId,
                                      UUID leasingAssetId,
                                      UUID leaseServiceEventId,
                                      LeaseServiceEventDTO dto);

    /**
     * Deletes a specific lease service event identified by its ID, associated with a specific leasing asset
     * and leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement associated with the lease service event
     * @param leasingAssetId the unique identifier of the leasing asset associated with the lease service event
     * @param leaseServiceEventId the unique identifier of the lease service event to be deleted
     * @return a Mono signaling the completion of the deletion operation
     */
    Mono<Void> delete(UUID leasingAgreementId,
                      UUID leasingAssetId,
                      UUID leaseServiceEventId);
}