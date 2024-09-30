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
import java.time.LocalDate;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate reportDate;
    private String content;

}
