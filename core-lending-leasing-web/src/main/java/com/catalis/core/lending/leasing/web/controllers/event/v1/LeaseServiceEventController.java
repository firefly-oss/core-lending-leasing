package com.catalis.core.lending.leasing.web.controllers.event.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.leasing.core.services.event.v1.LeaseServiceEventService;
import com.catalis.core.lending.leasing.interfaces.dtos.event.v1.LeaseServiceEventDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/leasing-agreements/{leasingAgreementId}/assets/{leasingAssetId}/service-events")
@Tag(name = "LeaseServiceEvent", description = "Maintenance/repair events for a leased asset")
@RequiredArgsConstructor
public class LeaseServiceEventController {

    private final LeaseServiceEventService service;

    @GetMapping
    @Operation(summary = "List or search service events for a leased asset")
    public Mono<ResponseEntity<PaginationResponse<LeaseServiceEventDTO>>> findAll(
            @PathVariable Long leasingAgreementId,
            @PathVariable Long leasingAssetId,
            @ModelAttribute FilterRequest<LeaseServiceEventDTO> filterRequest) {

        return service.findAll(leasingAgreementId, leasingAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new service event for a leased asset")
    public Mono<ResponseEntity<LeaseServiceEventDTO>> create(
            @PathVariable Long leasingAgreementId,
            @PathVariable Long leasingAssetId,
            @RequestBody LeaseServiceEventDTO dto) {

        return service.create(leasingAgreementId, leasingAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{leaseServiceEventId}")
    @Operation(summary = "Get a service event by ID")
    public Mono<ResponseEntity<LeaseServiceEventDTO>> getById(
            @PathVariable Long leasingAgreementId,
            @PathVariable Long leasingAssetId,
            @PathVariable Long leaseServiceEventId) {

        return service.getById(leasingAgreementId, leasingAssetId, leaseServiceEventId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{leaseServiceEventId}")
    @Operation(summary = "Update a service event record")
    public Mono<ResponseEntity<LeaseServiceEventDTO>> update(
            @PathVariable Long leasingAgreementId,
            @PathVariable Long leasingAssetId,
            @PathVariable Long leaseServiceEventId,
            @RequestBody LeaseServiceEventDTO dto) {

        return service.update(leasingAgreementId, leasingAssetId, leaseServiceEventId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{leaseServiceEventId}")
    @Operation(summary = "Delete a service event record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long leasingAgreementId,
            @PathVariable Long leasingAssetId,
            @PathVariable Long leaseServiceEventId) {

        return service.delete(leasingAgreementId, leasingAssetId, leaseServiceEventId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}