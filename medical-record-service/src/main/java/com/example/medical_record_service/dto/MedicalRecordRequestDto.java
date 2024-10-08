package com.example.medical_record_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO for creating a new medical record for a pet. Contains information about the pet, the veterinarian, the diagnosis, and the treatment details.")
public class MedicalRecordRequestDto {

    @Schema(description = "Unique identifier for the medical record", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "ID of the pet", example = "pet123")
    @NotBlank(message = "Pet ID cannot be blank")
    private String petId;

    @Schema(description = "ID of the veterinarian", example = "vet456")
    @NotBlank(message = "Veterinarian ID cannot be blank")
    private String vetId;

    @Schema(description = "Date and time of the visit", example = "2023-09-28T14:30:00")
    @NotNull(message = "Visit date cannot be null")
    private LocalDateTime visitDate;

    @Schema(description = "Diagnosis of the pet", example = "Viral infection")
    @NotBlank(message = "Diagnosis cannot be blank")
    private String diagnosis;

    @Schema(description = "Treatment prescribed for the pet", example = "Antibiotics and rest")
    @NotBlank(message = "Treatment cannot be blank")
    private String treatment;
}
