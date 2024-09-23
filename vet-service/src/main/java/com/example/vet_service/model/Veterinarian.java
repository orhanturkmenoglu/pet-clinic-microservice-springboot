package com.example.vet_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veterinarian {

    @Id
    @UuidGenerator
    private String id;
    private String firstName;
    private String lastName;
    private String specialization; // uzmanlık
    private String phoneNumber;
    private String email;
    private String address;
}
