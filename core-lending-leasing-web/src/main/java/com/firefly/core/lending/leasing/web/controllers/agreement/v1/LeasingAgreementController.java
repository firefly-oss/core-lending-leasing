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


package com.firefly.core.lending.leasing.web.controllers.agreement.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.core.services.agreement.v1.LeasingAgreementService;
import com.firefly.core.lending.leasing.interfaces.dtos.agreement.v1.LeasingAgreementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/leasing-agreements")
@Tag(name = "LeasingAgreement", description = "Operations on finance leasing agreements")
@RequiredArgsConstructor
public class LeasingAgreementController {

    private final LeasingAgreementService service;

    @GetMapping
    @Operation(summary = "List or search leasing agreements")
    public Mono<ResponseEntity<PaginationResponse<LeasingAgreementDTO>>> findAll(
            @ModelAttribute FilterRequest<LeasingAgreementDTO> filterRequest) {

        return service.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new leasing agreement")
    public Mono<ResponseEntity<LeasingAgreementDTO>> create(@RequestBody LeasingAgreementDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{leasingAgreementId}")
    @Operation(summary = "Get a leasing agreement by ID")
    public Mono<ResponseEntity<LeasingAgreementDTO>> getById(@PathVariable UUID leasingAgreementId) {
        return service.getById(leasingAgreementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{leasingAgreementId}")
    @Operation(summary = "Update a leasing agreement")
    public Mono<ResponseEntity<LeasingAgreementDTO>> update(
            @PathVariable UUID leasingAgreementId,
            @RequestBody LeasingAgreementDTO dto) {

        return service.update(leasingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{leasingAgreementId}")
    @Operation(summary = "Delete a leasing agreement")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID leasingAgreementId) {
        return service.delete(leasingAgreementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}