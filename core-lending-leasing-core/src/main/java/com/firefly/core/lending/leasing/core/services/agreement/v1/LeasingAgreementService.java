package com.firefly.core.lending.leasing.core.services.agreement.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.interfaces.dtos.agreement.v1.LeasingAgreementDTO;
import reactor.core.publisher.Mono;

public interface LeasingAgreementService {

    /**
     * Retrieves a paginated list of leasing agreements based on the provided filter criteria.
     *
     * @param filterRequest the filtering and pagination information including criteria for retrieving leasing agreements
     * @return a reactive Mono containing a PaginationResponse with a list of LeasingAgreementDTO objects
     */
    Mono<PaginationResponse<LeasingAgreementDTO>> findAll(FilterRequest<LeasingAgreementDTO> filterRequest);

    /**
     * Creates a new Leasing Agreement.
     *
     * @param dto the LeasingAgreementDTO object containing the details of the agreement to be created
     * @return a Mono emitting the created LeasingAgreementDTO object
     */
    Mono<LeasingAgreementDTO> create(LeasingAgreementDTO dto);

    /**
     * Retrieves a Leasing Agreement by its ID.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement to be retrieved
     * @return a {@link Mono} containing the {@code LeasingAgreementDTO} if found, or empty if not found
     */
    Mono<LeasingAgreementDTO> getById(Long leasingAgreementId);

    /**
     * Updates an existing leasing agreement with the specified details.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement to be updated
     * @param dto the data transfer object containing updated details for the leasing agreement
     * @return a Mono emitting the updated LeasingAgreementDTO upon successful update
     */
    Mono<LeasingAgreementDTO> update(Long leasingAgreementId, LeasingAgreementDTO dto);

    /**
     * Deletes the leasing agreement identified by the provided leasing agreement ID.
     *
     * @param leasingAgreementId the ID of the leasing agreement to be deleted
     * @return a Mono signaling when the deletion is completed
     */
    Mono<Void> delete(Long leasingAgreementId);
}