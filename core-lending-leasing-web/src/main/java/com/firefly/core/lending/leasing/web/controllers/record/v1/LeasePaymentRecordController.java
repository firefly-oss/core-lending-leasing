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

@RestController
@RequestMapping("/api/v1/leasing-agreements/{leasingAgreementId}/payments")
@Tag(name = "LeasePaymentRecord", description = "Payment records for a finance lease agreement")
@RequiredArgsConstructor
public class LeasePaymentRecordController {

    private final LeasePaymentRecordService service;

    @GetMapping
    @Operation(summary = "List or search lease payments for an agreement")
    public Mono<ResponseEntity<PaginationResponse<LeasePaymentRecordDTO>>> findAll(
            @PathVariable Long leasingAgreementId,
            @ModelAttribute FilterRequest<LeasePaymentRecordDTO> filterRequest) {

        return service.findAll(leasingAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new lease payment record")
    public Mono<ResponseEntity<LeasePaymentRecordDTO>> create(
            @PathVariable Long leasingAgreementId,
            @RequestBody LeasePaymentRecordDTO dto) {

        return service.create(leasingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{leasePaymentRecordId}")
    @Operation(summary = "Get a payment record by ID")
    public Mono<ResponseEntity<LeasePaymentRecordDTO>> getById(
            @PathVariable Long leasingAgreementId,
            @PathVariable Long leasePaymentRecordId) {

        return service.getById(leasingAgreementId, leasePaymentRecordId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{leasePaymentRecordId}")
    @Operation(summary = "Update a lease payment record")
    public Mono<ResponseEntity<LeasePaymentRecordDTO>> update(
            @PathVariable Long leasingAgreementId,
            @PathVariable Long leasePaymentRecordId,
            @RequestBody LeasePaymentRecordDTO dto) {

        return service.update(leasingAgreementId, leasePaymentRecordId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{leasePaymentRecordId}")
    @Operation(summary = "Delete a lease payment record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long leasingAgreementId,
            @PathVariable Long leasePaymentRecordId) {

        return service.delete(leasingAgreementId, leasePaymentRecordId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}