package com.example.billing_service.dto;

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
public class BillingResponseDto implements Serializable {

    private String id;
    private String ownerId;
    private String appointmentId;
    private Double amount;
    private String status;
    private LocalDateTime paymentDate;
}
