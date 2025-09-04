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


package com.firefly.core.lending.leasing.web.controllers.event.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.core.services.event.v1.LeaseServiceEventService;
import com.firefly.core.lending.leasing.interfaces.dtos.event.v1.LeaseServiceEventDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/leasing-agreements/{leasingAgreementId}/assets/{leasingAssetId}/service-events")
@Tag(name = "LeaseServiceEvent", description = "Maintenance/repair events for a leased asset")
@RequiredArgsConstructor
public class LeaseServiceEventController {

    private final LeaseServiceEventService service;

    @GetMapping
    @Operation(summary = "List or search service events for a leased asset")
    public Mono<ResponseEntity<PaginationResponse<LeaseServiceEventDTO>>> findAll(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leasingAssetId,
            @ModelAttribute FilterRequest<LeaseServiceEventDTO> filterRequest) {

        return service.findAll(leasingAgreementId, leasingAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new service event for a leased asset")
    public Mono<ResponseEntity<LeaseServiceEventDTO>> create(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leasingAssetId,
            @RequestBody LeaseServiceEventDTO dto) {

        return service.create(leasingAgreementId, leasingAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{leaseServiceEventId}")
    @Operation(summary = "Get a service event by ID")
    public Mono<ResponseEntity<LeaseServiceEventDTO>> getById(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leasingAssetId,
            @PathVariable UUID leaseServiceEventId) {

        return service.getById(leasingAgreementId, leasingAssetId, leaseServiceEventId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{leaseServiceEventId}")
    @Operation(summary = "Update a service event record")
    public Mono<ResponseEntity<LeaseServiceEventDTO>> update(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leasingAssetId,
            @PathVariable UUID leaseServiceEventId,
            @RequestBody LeaseServiceEventDTO dto) {

        return service.update(leasingAgreementId, leasingAssetId, leaseServiceEventId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{leaseServiceEventId}")
    @Operation(summary = "Delete a service event record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leasingAssetId,
            @PathVariable UUID leaseServiceEventId) {

        return service.delete(leasingAgreementId, leasingAssetId, leaseServiceEventId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}