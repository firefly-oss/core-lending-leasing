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


package com.firefly.core.lending.leasing.web.controllers.record.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.core.services.record.v1.LeasePaymentRecordService;
import com.firefly.core.lending.leasing.interfaces.dtos.record.v1.LeasePaymentRecordDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/leasing-agreements/{leasingAgreementId}/payments")
@Tag(name = "LeasePaymentRecord", description = "Payment records for a finance lease agreement")
@RequiredArgsConstructor
public class LeasePaymentRecordController {

    private final LeasePaymentRecordService service;

    @GetMapping
    @Operation(summary = "List or search lease payments for an agreement")
    public Mono<ResponseEntity<PaginationResponse<LeasePaymentRecordDTO>>> findAll(
            @PathVariable UUID leasingAgreementId,
            @ModelAttribute FilterRequest<LeasePaymentRecordDTO> filterRequest) {

        return service.findAll(leasingAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new lease payment record")
    public Mono<ResponseEntity<LeasePaymentRecordDTO>> create(
            @PathVariable UUID leasingAgreementId,
            @RequestBody LeasePaymentRecordDTO dto) {

        return service.create(leasingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{leasePaymentRecordId}")
    @Operation(summary = "Get a payment record by ID")
    public Mono<ResponseEntity<LeasePaymentRecordDTO>> getById(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leasePaymentRecordId) {

        return service.getById(leasingAgreementId, leasePaymentRecordId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{leasePaymentRecordId}")
    @Operation(summary = "Update a lease payment record")
    public Mono<ResponseEntity<LeasePaymentRecordDTO>> update(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leasePaymentRecordId,
            @RequestBody LeasePaymentRecordDTO dto) {

        return service.update(leasingAgreementId, leasePaymentRecordId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{leasePaymentRecordId}")
    @Operation(summary = "Delete a lease payment record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leasePaymentRecordId) {

        return service.delete(leasingAgreementId, leasePaymentRecordId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}