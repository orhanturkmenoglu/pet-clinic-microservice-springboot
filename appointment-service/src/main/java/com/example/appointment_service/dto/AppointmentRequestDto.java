package com.example.appointment_service.dto;


import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO for creating a new appointment. Contains details about the pet, veterinarian, appointment date, reason, and status.")
public class AppointmentRequestDto implements Serializable {

    @Schema(description = "Unique identifier for the appointment", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "ID of the pet for the appointment", example = "pet123")
    private String petId;

    @Schema(description = "ID of the veterinarian handling the appointment", example = "vet456")
    private String vetId;

    @Schema(description = "Date and time of the appointment", example = "2023-10-15T10:00:00")
    private LocalDateTime appointmentDate;

    @Schema(description = "Reason for the appointment", example = "Routine check-up")
    private String reason;

    @Schema(description = "Status of the appointment (e.g., SCHEDULED, COMPLETED, CANCELLED)", example = "SCHEDULED")
    private String status;
}
