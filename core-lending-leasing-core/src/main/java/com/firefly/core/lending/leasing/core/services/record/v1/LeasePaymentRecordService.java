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


package com.firefly.core.lending.leasing.core.services.record.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.interfaces.dtos.record.v1.LeasePaymentRecordDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface LeasePaymentRecordService {

    /**
     * Retrieves a paginated list of lease payment records associated with a specific leasing agreement ID
     * based on the provided filtering and pagination criteria.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement for which lease payment records are to be retrieved
     * @param filterRequest the filtering and pagination criteria used to retrieve the lease payment records
     * @return a reactive Mono containing a PaginationResponse with a list of LeasePaymentRecordDTO objects
     */
    Mono<PaginationResponse<LeasePaymentRecordDTO>> findAll(UUID leasingAgreementId,
                                                            FilterRequest<LeasePaymentRecordDTO> filterRequest);

    /**
     * Creates a new lease payment record associated with a specific leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement to which the lease payment record belongs
     * @param dto the data transfer object containing the details of the lease payment record to be created
     * @return a Mono emitting the created LeasePaymentRecordDTO object upon successful creation
     */
    Mono<LeasePaymentRecordDTO> create(UUID leasingAgreementId, LeasePaymentRecordDTO dto);

    /**
     *
     */
    Mono<LeasePaymentRecordDTO> getById(UUID leasingAgreementId, UUID leasePaymentRecordId);

    /**
     * Updates an existing lease payment record associated with a specific leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement associated with the payment record
     * @param leasePaymentRecordId the unique identifier of the lease payment record to be updated
     * @param dto the data transfer object containing updated details for the lease payment record
     * @return a Mono emitting the updated LeasePaymentRecordDTO upon successful update
     */
    Mono<LeasePaymentRecordDTO> update(UUID leasingAgreementId, UUID leasePaymentRecordId,
                                       LeasePaymentRecordDTO dto);

    /**
     * Deletes a specific lease payment record associated with a leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement associated with the lease payment record
     * @param leasePaymentRecordId the unique identifier of the lease payment record to be deleted
     * @return a Mono signaling the completion of the deletion process
     */
    Mono<Void> delete(UUID leasingAgreementId, UUID leasePaymentRecordId);
}
