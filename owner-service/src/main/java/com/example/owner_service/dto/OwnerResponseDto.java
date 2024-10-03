package com.example.owner_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object for Owner response")
public class OwnerResponseDto implements Serializable {

    @Schema(description = "Unique identifier of the owner", example = "a1b2c3d4-e5f6-7890-gh12-ijkl3456mnop")
    private String id;

    @Schema(description = "First name of the owner", example = "John")
    private String firstName;

    @Schema(description = "Last name of the owner", example = "Doe")
    private String lastName;

    @Schema(description = "Phone number of the owner", example = "+1234567890")
    private String phoneNumber;

    @Schema(description = "Email address of the owner", example = "johndoe@example.com")
    private String email;

    @Schema(description = "Address of the owner", example = "123 Main St, Springfield")
    private String address;

    @Schema(description = "Date and time when the owner was created", example = "2023-05-01T10:30:00")
    private LocalDateTime ownerDate;

    @Schema(description = "Date and time when the owner was updatedAt", example = "2023-05-01T10:30:00")
    private LocalDateTime updatedAt;

}