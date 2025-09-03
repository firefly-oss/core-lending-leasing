package com.firefly.core.lending.leasing.core.services.schedule.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.interfaces.dtos.schedule.v1.LeaseInstallmentScheduleDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface LeaseInstallmentScheduleService {

    /**
     * Retrieves a paginated list of lease installment schedules associated with a specific leasing agreement ID
     * based on the provided filtering and pagination criteria.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement for which lease installment schedules are to be retrieved
     * @param filterRequest the filtering and pagination criteria used to retrieve the lease installment schedules
     * @return a reactive Mono containing a PaginationResponse with a list of LeaseInstallmentScheduleDTO objects
     */
    Mono<PaginationResponse<LeaseInstallmentScheduleDTO>> findAll(UUID leasingAgreementId,
                                                                  FilterRequest<LeaseInstallmentScheduleDTO> filterRequest);

    /**
     * Creates a new lease installment schedule associated with a specified leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement to which the installment schedule is related
     * @param dto the data transfer object containing the details of the lease installment schedule to be created
     * @return a Mono emitting the created LeaseInstallmentScheduleDTO object upon successful creation
     */
    Mono<LeaseInstallmentScheduleDTO> create(UUID leasingAgreementId, LeaseInstallmentScheduleDTO dto);

    /**
     * Retrieves a specific Lease Installment Schedule by its associated Leasing Agreement ID
     * and Lease Installment Schedule ID.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement associated with the installment schedule
     * @param leaseInstallmentScheduleId the unique identifier of the lease installment schedule to be retrieved
     * @return a Mono emitting the LeaseInstallmentScheduleDTO corresponding to the provided IDs, or empty if not found
     */
    Mono<LeaseInstallmentScheduleDTO> getById(UUID leasingAgreementId, UUID leaseInstallmentScheduleId);

    /**
     * Updates an existing lease installment schedule associated with a specific leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement associated with the installment schedule
     * @param leaseInstallmentScheduleId the unique identifier of the lease installment schedule to be updated
     * @param dto the data transfer object containing updated details for the lease installment schedule
     * @return a Mono emitting the updated LeaseInstallmentScheduleDTO upon successful update
     */
    Mono<LeaseInstallmentScheduleDTO> update(UUID leasingAgreementId, UUID leaseInstallmentScheduleId,
                                             LeaseInstallmentScheduleDTO dto);

    /**
     * Deletes a specific lease installment schedule associated with a leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement to which the installment schedule belongs
     * @param leaseInstallmentScheduleId the unique identifier of the lease installment schedule to be deleted
     * @return a Mono signaling the completion of the deletion process
     */
    Mono<Void> delete(UUID leasingAgreementId, UUID leaseInstallmentScheduleId);
}
