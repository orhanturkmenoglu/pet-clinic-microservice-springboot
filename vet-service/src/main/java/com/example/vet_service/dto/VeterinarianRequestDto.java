package com.example.vet_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Data Transfer Object for Veterinarian request")
public class VeterinarianRequestDto implements Serializable {

    @Schema(description = "Unique identifier of the veterinarian", example = "a1b2c3d4-e5f6-7890-gh12-ijkl3456mnop")
    private String id;

    @Schema(description = "First name of the veterinarian", example = "Alice")
    @NotBlank(message = "First name is required")
    @Size(max = 250, message = "First name must not exceed 250 characters")
    private String firstName;

    @Schema(description = "Last name of the veterinarian", example = "Smith")
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @Schema(description = "Specialization of the veterinarian", example = "Surgery")
    @Size(max = 100, message = "Specialization must not exceed 100 characters")
    private String specialization;

    @Schema(description = "Availability status of the veterinarian", example = "Available")
    @NotBlank(message = "Availability status is required")
    private String availability;

    @Schema(description = "Phone number of the veterinarian", example = "+1234567890")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must be between 10 and 15 digits and can start with '+'")
    private String phoneNumber;

    @Schema(description = "Email address of the veterinarian", example = "alicesmith@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Address of the veterinarian", example = "456 Elm St, Springfield")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;


}