package com.example.medical_record_service.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response DTO for returning medical record details of a pet. Includes information about the pet, veterinarian, visit date, diagnosis, and treatment.")
public class MedicalRecordResponseDto {

    @Schema(description = "Unique identifier for the medical record", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "ID of the pet associated with the medical record", example = "pet123")
    private String petId;

    @Schema(description = "ID of the veterinarian who handled the visit", example = "vet456")
    private String vetId;

    @Schema(description = "Date and time when the pet visited the veterinarian", example = "2023-09-28T14:30:00")
    private LocalDateTime visitDate;

    @Schema(description = "Diagnosis provided by the veterinarian", example = "Viral infection")
    private String diagnosis;

    @Schema(description = "Treatment prescribed by the veterinarian", example = "Antibiotics and rest")
    private String treatment;
}
