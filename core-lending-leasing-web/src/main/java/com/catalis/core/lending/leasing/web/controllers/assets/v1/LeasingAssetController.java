package com.catalis.core.lending.leasing.web.controllers.assets.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.leasing.core.services.assets.v1.LeasingAssetService;
import com.catalis.core.lending.leasing.interfaces.dtos.assets.v1.LeasingAssetDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/leasing-agreements/{leasingAgreementId}/assets")
@Tag(name = "LeasingAsset", description = "Operations on leased assets under a leasing agreement")
@RequiredArgsConstructor
public class LeasingAssetController {

    private final LeasingAssetService service;

    @GetMapping
    @Operation(summary = "List or search leased assets for a leasing agreement")
    public Mono<ResponseEntity<PaginationResponse<LeasingAssetDTO>>> findAll(
            @PathVariable Long leasingAgreementId,
            @ModelAttribute FilterRequest<LeasingAssetDTO> filterRequest) {

        return service.findAll(leasingAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new asset record under a leasing agreement")
    public Mono<ResponseEntity<LeasingAssetDTO>> create(
            @PathVariable Long leasingAgreementId,
            @RequestBody LeasingAssetDTO dto) {

        return service.create(leasingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{leasingAssetId}")
    @Operation(summary = "Get a leased asset by ID")
    public Mono<ResponseEntity<LeasingAssetDTO>> getById(
            @PathVariable Long leasingAgreementId,
            @PathVariable Long leasingAssetId) {

        return service.getById(leasingAgreementId, leasingAssetId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{leasingAssetId}")
    @Operation(summary = "Update a leased asset record")
    public Mono<ResponseEntity<LeasingAssetDTO>> update(
            @PathVariable Long leasingAgreementId,
            @PathVariable Long leasingAssetId,
            @RequestBody LeasingAssetDTO dto) {

        return service.update(leasingAgreementId, leasingAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{leasingAssetId}")
    @Operation(summary = "Delete a leased asset record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long leasingAgreementId,
            @PathVariable Long leasingAssetId) {

        return service.delete(leasingAgreementId, leasingAssetId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
