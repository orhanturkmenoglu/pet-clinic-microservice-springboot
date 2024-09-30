package com.example.medical_record_service.controller;

import com.example.medical_record_service.dto.MedicalRecordRequestDto;
import com.example.medical_record_service.dto.MedicalRecordResponseDto;
import com.example.medical_record_service.service.MedicalRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/medical_records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @Operation(summary = "Create a new medical record",
            description = "Creates a new medical record for a patient based on the provided information.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Medical record created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data"),
            }
    )
    @PostMapping
    public ResponseEntity<MedicalRecordResponseDto> createMedicalRecord(
            @Parameter(description = "Medical record data", required = true)
            @RequestBody MedicalRecordRequestDto medicalRecordRequestDto) {

        MedicalRecordResponseDto medicalRecord = medicalRecordService.createMedicalRecord(medicalRecordRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecord);
    }
}
