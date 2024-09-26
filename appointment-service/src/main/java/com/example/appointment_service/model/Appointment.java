package com.example.appointment_service.model;

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
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment implements Serializable {

    @Id
    @UuidGenerator
    private String id;
    private String petId;
    private String vetId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime appointmentDate;
    private String reason;
    private String status;
}
