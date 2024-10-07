package com.example.report_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Data Transfer Object for Report request")
public class ReportRequestDto implements Serializable {

    @Schema(description = "Unique identifier for the report", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Unique identifier for the pet", example = "pet-001")
    @NotBlank(message = "Pet ID cannot be blank")
    private String petId;

    @Schema(description = "Unique identifier for the vet associated with the report", example = "vet-001")
    @NotBlank(message = "Vet ID cannot be blank")
    private String vetId;

    @Schema(description = "Unique identifier for the billing information", example = "billing-001")
    @NotBlank(message = "Billing ID cannot be blank")
    private String billingId;

    @Schema(description = "Unique identifier for the appointment associated with the report", example = "appointment-001")
    @NotBlank(message = "Appointment ID cannot be blank")
    private String appointmentId;

    @Schema(description = "Unique identifier for the medical record associated with the report", example = "medical-record-001")
    @NotBlank(message = "Medical Record ID cannot be blank")
    private String medicalRecordId;

    @Schema(description = "Status of the report", example = "COMPLETED", allowableValues = {"PENDING", "COMPLETED", "FAILED"})
    @NotBlank(message = "Status cannot be blank")
    private String status;

    @Schema(description = "Detailed content of the report", example = "Patient presented with symptoms of...")
    @Size(max = 1000, message = "Content must not exceed 1000 characters")
    private String content;

    @Schema(description = "Type of the report", example = "APPOINTMENT", allowableValues = {"APPOINTMENT", "MEDICAL_RECORD"})
    @NotBlank(message = "Report Type cannot be blank")
    private String reportType;

    @Schema(description = "Date when the report was generated", example = "2024-01-01T10:00:00")
    @NotNull(message = "Report Date cannot be null")
    private LocalDateTime reportDate;

    @Schema(description = "Date when the report was last updated", example = "2024-01-01T12:00:00")
    @NotNull(message = "Updated At cannot be null")
    private LocalDateTime updatedAt;
}
