package com.example.medical_record_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordResponseDto {

    private String id;
    private String petId;
    private String vetId;
    private LocalDate visitDate;
    private String diagnosis;
    private String treatment;
}
