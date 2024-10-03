package com.example.medical_record_service.controller;

import com.example.medical_record_service.dto.MedicalRecordRequestDto;
import com.example.medical_record_service.dto.MedicalRecordResponseDto;
import com.example.medical_record_service.service.MedicalRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medical_records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @Operation(summary = "Create a new medical record",
            description = "Creates a new medical record for a patient based on the provided information.")
    @PostMapping
    public ResponseEntity<MedicalRecordResponseDto> createMedicalRecord(
            @Parameter(description = "Medical record data", required = true)
            @RequestBody MedicalRecordRequestDto medicalRecordRequestDto) {

        MedicalRecordResponseDto medicalRecord = medicalRecordService.createMedicalRecord(medicalRecordRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecord);
    }

    @Operation(summary = "Get all medical records")
    @GetMapping
    public ResponseEntity<List<MedicalRecordResponseDto>> getAllMedicalRecords() {
        List<MedicalRecordResponseDto> medicalRecords = medicalRecordService.getAllMedicalRecords();
        return ResponseEntity.ok(medicalRecords);
    }

    @Operation(summary = "Get medical record by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDto> getMedicalRecordById(
            @Parameter(description = "ID of the medical record to retrieve")
            @PathVariable String id) {
        MedicalRecordResponseDto medicalRecord = medicalRecordService.getMedicalRecordById(id);
        return ResponseEntity.ok(medicalRecord);
    }

    @Operation(summary = "Get medical records by pet ID")
    @GetMapping("/pets/{petId}")
    public ResponseEntity<List<MedicalRecordResponseDto>> getByPetId(
            @Parameter(description = "ID of the pet to retrieve medical records for")
            @PathVariable String petId) {
        List<MedicalRecordResponseDto> medicalRecords = medicalRecordService.getByPetId(petId);
        return ResponseEntity.ok(medicalRecords);
    }

    @Operation(summary = "Get medical records by vet ID")
    @GetMapping("/vets/{vetId}")
    public ResponseEntity<List<MedicalRecordResponseDto>> getByVetId(
            @Parameter(description = "ID of the vet to retrieve medical records for")
            @PathVariable String vetId) {
        List<MedicalRecordResponseDto> medicalRecords = medicalRecordService.getByVetId(vetId);
        return ResponseEntity.ok(medicalRecords);
    }

    @Operation(summary = "Get medical records by visit date range")
    @GetMapping("/dates")
    public ResponseEntity<List<MedicalRecordResponseDto>> findByVisitDateBetween(
            @Parameter(description = "Start date of the visit range")
            @RequestParam LocalDateTime startDate,
            @Parameter(description = "End date of the visit range")
            @RequestParam LocalDateTime endDate) {
        List<MedicalRecordResponseDto> medicalRecords = medicalRecordService.findByVisitDateBetween(startDate, endDate);
        return ResponseEntity.ok(medicalRecords);
    }

    @Operation(summary = "Update a medical record")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDto> updateMedicalRecord(
            @Parameter(description = "ID of the medical record to update")
            @PathVariable String id,
            @Parameter(description = "Updated medical record data")
            @RequestBody MedicalRecordRequestDto medicalRecordRequestDto) {
        MedicalRecordResponseDto updatedRecord = medicalRecordService.updateMedicalRecord(id, medicalRecordRequestDto);
        return ResponseEntity.ok(updatedRecord);
    }

    @Operation(summary = "Delete a medical record")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(
            @Parameter(description = "ID of the medical record to delete")
            @PathVariable String id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get medical records updated after a certain date")
    @GetMapping("/updated-after")
    public ResponseEntity<List<MedicalRecordResponseDto>> findByUpdatedAtAfter(
            @Parameter(description = "Date to filter medical records updated after")
            @RequestParam LocalDateTime date) {
        List<MedicalRecordResponseDto> medicalRecords = medicalRecordService.findByUpdatedAtAfter(date);
        return ResponseEntity.ok(medicalRecords);
    }


}
