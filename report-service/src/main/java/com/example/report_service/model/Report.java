package com.example.report_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table (name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report implements Serializable {

    @Id
    @UuidGenerator
    private String id;
    private String petId;
    private String vetId;     // İlgili veterinerin ID'si
    private String billingId; // Faturalandırma servisi ile ilişkili ID
    private String appointmentId; // İlgili randevunun ID'si
    private String medicalRecordId; // İlgili tıbbi kaydın ID'si
    private String status; // Rapor durumu (örneğin, "PENDING", "COMPLETED", "FAILED")
    private String content;
    private String reportType; // Rapor türü (örneğin, "APPOINTMENT", "MEDICAL_RECORD")

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime reportDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt; // Raporun güncellenme zamanı

}
