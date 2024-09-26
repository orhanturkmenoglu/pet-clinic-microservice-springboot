package com.example.appointment_service.dto;

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
public class AppointmentResponseDto implements Serializable {

    private String id;
    private String petId;
    private String vetId;
    private LocalDateTime appointmentDate;
    private String reason;
    private String status;
}
