package com.firefly.core.lending.leasing.web.controllers.schedule.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.leasing.core.services.schedule.v1.LeaseInstallmentScheduleService;
import com.firefly.core.lending.leasing.interfaces.dtos.schedule.v1.LeaseInstallmentScheduleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/leasing-agreements/{leasingAgreementId}/installments")
@Tag(name = "LeaseInstallmentSchedule", description = "Installment schedule for a finance lease agreement")
@RequiredArgsConstructor
public class LeaseInstallmentScheduleController {

    private final LeaseInstallmentScheduleService service;

    @GetMapping
    @Operation(summary = "List or search lease installment schedules for an agreement")
    public Mono<ResponseEntity<PaginationResponse<LeaseInstallmentScheduleDTO>>> findAll(
            @PathVariable UUID leasingAgreementId,
            @ModelAttribute FilterRequest<LeaseInstallmentScheduleDTO> filterRequest) {

        return service.findAll(leasingAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new installment schedule entry")
    public Mono<ResponseEntity<LeaseInstallmentScheduleDTO>> create(
            @PathVariable UUID leasingAgreementId,
            @RequestBody LeaseInstallmentScheduleDTO dto) {

        return service.create(leasingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{leaseInstallmentScheduleId}")
    @Operation(summary = "Get an installment schedule entry by ID")
    public Mono<ResponseEntity<LeaseInstallmentScheduleDTO>> getById(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leaseInstallmentScheduleId) {

        return service.getById(leasingAgreementId, leaseInstallmentScheduleId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{leaseInstallmentScheduleId}")
    @Operation(summary = "Update an installment schedule record")
    public Mono<ResponseEntity<LeaseInstallmentScheduleDTO>> update(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leaseInstallmentScheduleId,
            @RequestBody LeaseInstallmentScheduleDTO dto) {

        return service.update(leasingAgreementId, leaseInstallmentScheduleId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{leaseInstallmentScheduleId}")
    @Operation(summary = "Delete an installment schedule entry")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID leasingAgreementId,
            @PathVariable UUID leaseInstallmentScheduleId) {

        return service.delete(leasingAgreementId, leaseInstallmentScheduleId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
