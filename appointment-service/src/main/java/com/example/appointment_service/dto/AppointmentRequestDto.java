package com.example.appointment_service.dto;


import com.example.appointment_service.anotations.ValidCustom;
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
    @ValidCustom(message = "Pet ID cannot be blank")
    private String petId;

    @Schema(description = "ID of the veterinarian handling the appointment", example = "vet456")
    @ValidCustom(message = "Vet ID cannot be blank")
    private String vetId;

    @Schema(description = "Date and time of the appointment", example = "2023-10-15T10:00:00")
    private LocalDateTime appointmentDate;

    @Schema(description = "Reason for the appointment", example = "Routine check-up")
    @ValidCustom(message = "Reason  cannot be blank")
    private String reason;

    @Schema(description = "Status of the appointment (e.g., SCHEDULED, COMPLETED, CANCELLED)", example = "SCHEDULED")
    @ValidCustom(message = "Status  cannot be blank")
    private String status;
}
