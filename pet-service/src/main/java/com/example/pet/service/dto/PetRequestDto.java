package com.example.pet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Data transfer object for pet request")
public class PetRequestDto implements Serializable {

    @Schema(description = "The owner id of the pet", example = "12345")
    @NotBlank(message = "Owner ID cannot be blank")
    private String ownerId;  // sahip kimliği.

    @Schema(description = "The name of the pet", example = "Buddy")
    @NotBlank(message = "Pet name cannot be blank")
    private String name;

    @Schema(description = "The type of the pet (e.g., Dog, Cat)", example = "Dog")
    @NotBlank(message = "Pet type cannot be blank")
    private String type;

    @Schema(description = "The breed of the pet", example = "Golden Retriever")
    @NotBlank(message = "Pet breed cannot be blank")
    private String breed;

    @Schema(description = "The age of the pet", example = "2 years")
    @NotBlank(message = "Pet age cannot be blank")
    private String age;

    @Schema(description = "The gender of the pet", example = "Male")
    private String gender;

    @Schema(description = "The color of the pet", example = "Golden")
    @NotBlank(message = "Pet gender cannot be blank")
    private String color;

    @Schema(description = "The weight of the pet", example = "15.5")
    @NotBlank(message = "Pet color cannot be blank")
    private Double weight;

    @Schema(description = "A description of the pet", example = "Friendly and playful")
    @NotBlank(message = "Pet color cannot be blank")
    private String description;
}