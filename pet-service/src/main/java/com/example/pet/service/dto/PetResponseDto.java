package com.example.pet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Data transfer object for pet response")
public class PetResponseDto implements Serializable {

    @Schema(description = "The name of the pet", example = "Buddy")
    private String name;

    @Schema(description = "The type of the pet (e.g., Dog, Cat)", example = "Dog")
    private String type;

    @Schema(description = "The breed of the pet", example = "Golden Retriever")
    private String breed;

    @Schema(description = "The age of the pet", example = "2 years")
    private String age;

    @Schema(description = "The gender of the pet", example = "Male")
    private String gender;

    @Schema(description = "The color of the pet", example = "Golden")
    private String color;

    @Schema(description = "A description of the pet", example = "Friendly and playful")
    private String description;
}