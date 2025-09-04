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


package com.firefly.core.lending.leasing.web.controllers.option.v1;

import com.firefly.core.lending.leasing.core.services.option.v1.LeaseEndOptionService;
import com.firefly.core.lending.leasing.interfaces.dtos.option.v1.LeaseEndOptionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/leasing-agreements/{leasingAgreementId}/end-option")
@Tag(name = "LeaseEndOption", description = "End-of-lease purchase or return option for a finance lease")
@RequiredArgsConstructor
public class LeaseEndOptionController {

    private final LeaseEndOptionService service;

    @GetMapping
    @Operation(summary = "Get the end-of-lease option details for an agreement")
    public Mono<ResponseEntity<LeaseEndOptionDTO>> getByAgreement(
            @PathVariable UUID leasingAgreementId) {

        return service.getByAgreement(leasingAgreementId)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create the end-of-lease option record (purchase option scenario)")
    public Mono<ResponseEntity<LeaseEndOptionDTO>> create(
            @PathVariable UUID leasingAgreementId,
            @RequestBody LeaseEndOptionDTO dto) {

        return service.create(leasingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @PutMapping
    @Operation(summary = "Update the end-of-lease option record")
    public Mono<ResponseEntity<LeaseEndOptionDTO>> update(
            @PathVariable UUID leasingAgreementId,
            @RequestBody LeaseEndOptionDTO dto) {

        return service.update(leasingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping
    @Operation(summary = "Delete the end-of-lease option record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID leasingAgreementId) {

        return service.delete(leasingAgreementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}