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


package com.firefly.core.lending.leasing.web.controllers.assets.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.core.services.assets.v1.LeasingAssetService;
import com.firefly.core.lending.leasing.interfaces.dtos.assets.v1.LeasingAssetDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/leasing-agreements/{leasingAgreementId}/assets")
@Tag(name = "LeasingAsset", description = "Operations on leased assets under a leasing agreement")
@RequiredArgsConstructor
public class LeasingAssetController {

    private final LeasingAssetService service;

    @GetMapping
    @Operation(summary = "List or search leased assets for a leasing agreement")
    public Mono<ResponseEntity<PaginationResponse<LeasingAssetDTO>>> findAll(
            @PathVariable UUID leasingAgreementId,
            @ModelAttribute FilterRequest<LeasingAssetDTO> filterRequest) {

        return service.findAll(leasingAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new asset record under a leasing agreement")
    public Mono<ResponseEntity<LeasingAssetDTO>> create(
            @PathVariable UUID leasingAgreementId,
            @RequestBody LeasingAssetDTO dto) {

        return service.create(leasingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{leasingAssetId}")
    @Operation(summary = "Get a leased asset by ID")
    public Mono<ResponseEntity<LeasingAssetDTO>> getById(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leasingAssetId) {

        return service.getById(leasingAgreementId, leasingAssetId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{leasingAssetId}")
    @Operation(summary = "Update a leased asset record")
    public Mono<ResponseEntity<LeasingAssetDTO>> update(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leasingAssetId,
            @RequestBody LeasingAssetDTO dto) {

        return service.update(leasingAgreementId, leasingAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{leasingAssetId}")
    @Operation(summary = "Delete a leased asset record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leasingAssetId) {

        return service.delete(leasingAgreementId, leasingAssetId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
