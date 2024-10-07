package com.example.owner_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Data Transfer Object for Owner request")
public class OwnerRequestDto implements Serializable {

    @Schema(description = "Unique identifier of the owner", example = "a1b2c3d4-e5f6-7890-gh12-ijkl3456mnop")
    private String id;

    @Schema(description = "First name of the owner", example = "John")
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @Schema(description = "Last name of the owner", example = "Doe")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Schema(description = "Phone number of the owner", example = "+1234567890")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @Schema(description = "Email address of the owner", example = "johndoe@example.com")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Schema(description = "Address of the owner", example = "123 Main St, Springfield")
    @NotBlank(message = "Address is mandatory")
    private String address;

}