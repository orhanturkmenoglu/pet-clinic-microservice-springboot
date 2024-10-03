package com.example.vet_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "veterinarians ")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veterinarian implements Serializable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "specialization", nullable = false)
    private String specialization; // uzmanlık

    @Column(name = "availability", nullable = false)
    private String availability;  // müsaitlik durumu

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Getter
    private LocalDateTime  veterinarianDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Getter
    private LocalDateTime  updatedAt;

    @PrePersist
    protected void onCreate() {
        this.veterinarianDate = LocalDateTime.now(); // Kayıt yapıldığında tarih otomatik olarak atanır
    }
}
