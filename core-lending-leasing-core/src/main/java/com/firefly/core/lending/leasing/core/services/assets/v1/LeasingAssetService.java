package com.firefly.core.lending.leasing.core.services.assets.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.interfaces.dtos.assets.v1.LeasingAssetDTO;
import reactor.core.publisher.Mono;

public interface LeasingAssetService {

    /**
     * Retrieves a paginated list of leasing assets associated with a specific leasing agreement ID
     * based on the provided filtering and pagination criteria.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement for which leasing assets are to be retrieved
     * @param filterRequest the filtering and pagination criteria used to retrieve the leasing assets
     * @return a reactive Mono containing a PaginationResponse with a list of LeasingAssetDTO objects
     */
    Mono<PaginationResponse<LeasingAssetDTO>> findAll(Long leasingAgreementId,
                                                      FilterRequest<LeasingAssetDTO> filterRequest);

    /**
     * Creates a new leasing asset associated with a specified leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement to which the asset is associated
     * @param dto the data transfer object containing the details of the leasing asset to be created
     * @return a Mono emitting the created LeasingAssetDTO object upon successful creation
     */
    Mono<LeasingAssetDTO> create(Long leasingAgreementId, LeasingAssetDTO dto);

    /**
     * Retrieves a specific Leasing Asset by its associated Leasing Agreement ID and Leasing Asset ID.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement associated with the leasing asset
     * @param leasingAssetId the unique identifier of the leasing asset to be retrieved
     * @return a Mono emitting the LeasingAssetDTO corresponding to the provided IDs, or empty if not found
     */
    Mono<LeasingAssetDTO> getById(Long leasingAgreementId, Long leasingAssetId);

    /**
     * Updates an existing leasing asset associated with a specific leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement associated with the asset
     * @param leasingAssetId the unique identifier of the leasing asset to be updated
     * @param dto the data transfer object containing updated details for the leasing asset
     * @return a Mono emitting the updated LeasingAssetDTO upon successful update
     */
    Mono<LeasingAssetDTO> update(Long leasingAgreementId, Long leasingAssetId, LeasingAssetDTO dto);

    /**
     * Deletes a leasing asset associated with a specific leasing agreement.
     *
     * @param leasingAgreementId the unique identifier of the leasing agreement to which the asset belongs
     * @param leasingAssetId the unique identifier of the leasing asset to be deleted
     * @return a Mono signaling the completion of the deletion process
     */
    Mono<Void> delete(Long leasingAgreementId, Long leasingAssetId);
}
