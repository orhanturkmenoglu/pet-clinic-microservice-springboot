package com.example.billing_service.controller;

import com.example.billing_service.dto.BillingRequestDto;
import com.example.billing_service.dto.BillingResponseDto;
import com.example.billing_service.service.BillingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/billings")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;

    @PostMapping
    @Operation(summary = "Create a new billing", description = "Creates a new billing record")
    public ResponseEntity<BillingResponseDto> createBilling(@RequestBody BillingRequestDto billingRequestDto) {
        BillingResponseDto createdBilling = billingService.createBilling(billingRequestDto);
        return new ResponseEntity<>(createdBilling, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all billings", description = "Retrieves all billing records")
    public ResponseEntity<List<BillingResponseDto>> getAllBillings() {
        List<BillingResponseDto> billings = billingService.getAllBillings();
        return ResponseEntity.ok(billings);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get billing by ID", description = "Retrieves a billing record by its ID")
    @Parameter(name = "id", description = "The ID of the billing record", required = true)
    public ResponseEntity<BillingResponseDto> getBillingById(@PathVariable String id) {
        BillingResponseDto billing = billingService.getBillingById(id);
        return ResponseEntity.ok(billing);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get billings by status", description = "Retrieves billing records by their status")
    @Parameter(name = "status", description = "The status of the billing records", required = true)
    public ResponseEntity<List<BillingResponseDto>> getBillingsByStatus(@PathVariable String status) {
        List<BillingResponseDto> billings = billingService.getBillingsByStatus(status);
        return ResponseEntity.ok(billings);
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "Get billings by owner ID", description = "Retrieves billing records for a specific owner")
    @Parameter(name = "ownerId", description = "The ID of the owner", required = true)
    public ResponseEntity<List<BillingResponseDto>> getBillingsByOwnerId(@PathVariable String ownerId) {
        List<BillingResponseDto> billings = billingService.getBillingsByOwnerId(ownerId);
        return ResponseEntity.ok(billings);
    }

    @GetMapping("/appointment/{appointmentId}")
    @Operation(summary = "Get billings by appointment ID", description = "Retrieves billing records for a specific appointment")
    @Parameter(name = "appointmentId", description = "The ID of the appointment", required = true)
    public ResponseEntity<List<BillingResponseDto>> getBillingsByAppointmentId(@PathVariable String appointmentId) {
        List<BillingResponseDto> billings = billingService.getBillingsByAppointmentId(appointmentId);
        return ResponseEntity.ok(billings);
    }

    @GetMapping("/payment")
    @Operation(summary = "Get billings by payment date", description = "Retrieves billing records based on payment date range")
    public ResponseEntity<List<BillingResponseDto>> getBillingsByPaymentDate(
            @RequestParam @Parameter(description = "Start date for payment range", required = true) LocalDateTime startDate,
            @RequestParam @Parameter(description = "End date for payment range", required = true) LocalDateTime endDate) {
        List<BillingResponseDto> billings = billingService.getBillingsByPaymentDate(startDate, endDate);
        return ResponseEntity.ok(billings);
    }

    @GetMapping("/updated")
    @Operation(summary = "Get updated billings after a specific date", description = "Retrieves billing records updated after a specific date")
    public ResponseEntity<List<BillingResponseDto>> getUpdatedBillingsAfter(
            @RequestParam @Parameter(description = "Date to filter updated billings", required = true) LocalDateTime updatedAt) {
        List<BillingResponseDto> billings = billingService.getUpdatedBillingsAfter(updatedAt);
        return ResponseEntity.ok(billings);
    }

    @GetMapping("/status/payment")
    @Operation(summary = "Get billings by status and payment date range", description = "Retrieves billing records by status and payment date range")
    public ResponseEntity<List<BillingResponseDto>> getBillingsByStatusAndPaymentDate(
            @RequestParam @Parameter(description = "The status of the billing records", required = true) String status,
            @RequestParam @Parameter(description = "Start date for payment range", required = true) LocalDateTime startDate,
            @RequestParam @Parameter(description = "End date for payment range", required = true) LocalDateTime endDate) {
        List<BillingResponseDto> billings = billingService.getBillingsByStatusAndPaymentDate(status, startDate, endDate);
        return ResponseEntity.ok(billings);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update billing by ID", description = "Updates a billing record by its ID")
    @Parameter(name = "id", description = "The ID of the billing record", required = true)
    public ResponseEntity<BillingResponseDto> updateBilling(@PathVariable String id, @RequestBody BillingRequestDto billingRequestDto) {
        BillingResponseDto updatedBilling = billingService.updateBilling(id, billingRequestDto);
        return ResponseEntity.ok(updatedBilling);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete billing by ID", description = "Deletes a billing record by its ID")
    @Parameter(name = "id", description = "The ID of the billing record", required = true)
    public ResponseEntity<Void> deleteBilling(@PathVariable String id) {
        billingService.deleteBilling(id);
        return ResponseEntity.noContent().build();
    }
}
