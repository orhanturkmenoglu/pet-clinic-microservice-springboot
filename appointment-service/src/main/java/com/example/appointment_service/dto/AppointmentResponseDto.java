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
@Schema(description = "Appointment response DTO containing details of a veterinary appointment")
public class AppointmentResponseDto implements Serializable {

    @Schema(description = "Unique identifier of the appointment", example = "a123b456-c789-0123-d456-789e0123f456")
    private String id;

    @Schema(description = "Unique identifier of the pet associated with the appointment", example = "p123b456-c789-0123-d456-789e0123f789")
    private String petId;

    @Schema(description = "Unique identifier of the veterinarian handling the appointment", example = "v123b456-c789-0123-d456-789e0123f111")
    private String vetId;

    @Schema(description = "Date and time of the appointment", example = "2024-10-05T14:30:00")
    private LocalDateTime appointmentDate;

    @Schema(description = "Updated at of the appointment", example = "2024-10-05T14:30:00")
    private LocalDateTime updatedAt;

    @Schema(description = "Reason for the appointment", example = "General check-up for vaccination")
    private String reason;

    @Schema(description = "Current status of the appointment", example = "Scheduled")
    private String status;
}
