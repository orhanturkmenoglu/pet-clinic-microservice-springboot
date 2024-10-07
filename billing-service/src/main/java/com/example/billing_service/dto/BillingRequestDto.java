package com.example.billing_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO for creating or updating a billing record. Contains billing details such as owner, appointment, amount, status, and payment date.")
public class BillingRequestDto implements Serializable {

    @Schema(description = "Unique identifier for the billing record", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "ID of the owner associated with the billing", example = "owner123")
    @NotBlank(message = "Owner ID cannot be blank")
    private String ownerId;

    @Schema(description = "ID of the appointment for which the billing is generated", example = "appointment456")
    @NotBlank(message = "Appointment ID cannot be blank")
    private String appointmentId;

    @Schema(description = "Amount to be billed", example = "250.00")
    @NotNull(message = "Amount cannot be null")
    @PositiveOrZero(message = "Amount must be positive or zero")
    private Double amount;

    @Schema(description = "Current status of the billing (e.g., PENDING, PAID, CANCELLED)", example = "PAID")
    private String status;

}
