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
    public Mono<ResponseEntity<LeasingAgreementDTO>> getById(@PathVariable Long leasingAgreementId) {
        return service.getById(leasingAgreementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{leasingAgreementId}")
    @Operation(summary = "Update a leasing agreement")
    public Mono<ResponseEntity<LeasingAgreementDTO>> update(
            @PathVariable Long leasingAgreementId,
            @RequestBody LeasingAgreementDTO dto) {

        return service.update(leasingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{leasingAgreementId}")
    @Operation(summary = "Delete a leasing agreement")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long leasingAgreementId) {
        return service.delete(leasingAgreementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}