package com.example.billing_service.dto;

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
@Schema(description = "DTO for the billing response. Contains billing details that are returned to the client.")
public class BillingResponseDto implements Serializable {

    @Schema(description = "Unique identifier for the billing record", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "ID of the owner associated with the billing", example = "owner123")
    private String ownerId;

    @Schema(description = "ID of the appointment for which the billing was generated", example = "appointment456")
    private String appointmentId;

    @Schema(description = "Amount billed", example = "250.00")
    private Double amount;

    @Schema(description = "Current status of the billing (e.g., PENDING, PAID, CANCELLED)", example = "PAID")
    private String status;

    @Schema(description = "Date and time of the payment", example = "2023-09-28T15:30:00")
    private LocalDateTime paymentDate;

    @Schema(description = "Updated at of the payment", example = "2023-09-28T15:30:00")
    private LocalDateTime updatedAt;
}
