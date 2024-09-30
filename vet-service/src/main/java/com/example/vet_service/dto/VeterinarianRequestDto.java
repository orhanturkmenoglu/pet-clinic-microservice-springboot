package com.example.vet_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Data Transfer Object for Veterinarian request")
public class VeterinarianRequestDto implements Serializable {

    @Schema(description = "Unique identifier of the veterinarian", example = "a1b2c3d4-e5f6-7890-gh12-ijkl3456mnop")
    private String id;

    @Schema(description = "First name of the veterinarian", example = "Alice")
    private String firstName;

    @Schema(description = "Last name of the veterinarian", example = "Smith")
    private String lastName;

    @Schema(description = "Specialization of the veterinarian", example = "Surgery")
    private String specialization;

    @Schema(description = "Availability status of the veterinarian", example = "Available")
    private String availability;

    @Schema(description = "Phone number of the veterinarian", example = "+1234567890")
    private String phoneNumber;

    @Schema(description = "Email address of the veterinarian", example = "alicesmith@example.com")
    private String email;

    @Schema(description = "Address of the veterinarian", example = "456 Elm St, Springfield")
    private String address;

    @Schema(description = "The date and time when the veterinarian was created", example = "2023-09-28T10:15:30")
    private LocalDateTime createdVeterinarianDate;
}