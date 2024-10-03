package com.example.pet.service.controller;

import com.example.pet.service.dto.PetRequestDto;
import com.example.pet.service.dto.PetResponseDto;
import com.example.pet.service.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    @Operation(summary = "Create a new pet")
    public ResponseEntity<PetResponseDto> createPet(
            @Parameter(description = "Pet request DTO containing pet details", required = true)
            @RequestBody PetRequestDto petRequestDto) {
        PetResponseDto petResponseDto = petService.createPet(petRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(petResponseDto);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all pets")
    public ResponseEntity<List<PetResponseDto>> getAllPets() {
        List<PetResponseDto> petResponseDtoList = petService.getAllPets();
        return ResponseEntity.ok(petResponseDtoList);
    }


    @GetMapping("/petType")
    @Operation(summary = "get all pets by type")
    public ResponseEntity<List<PetResponseDto>> getPetByType(
            @Parameter(description = "PetType", required = true)
            @RequestParam String petType) {
        List<PetResponseDto> petResponseDtoList = petService.getPetByType(petType);
        return ResponseEntity.ok(petResponseDtoList);
    }

    @GetMapping("/petId")
    @Operation(summary = "get pet by id")
    public ResponseEntity<PetResponseDto> getPetById(
            @Parameter(description = "Pet id", required = true)
            @RequestParam String petId) {
        PetResponseDto petResponseDtoList = petService.getPetById(petId);
        return ResponseEntity.ok(petResponseDtoList);
    }

    @GetMapping("/ownerId")
    @Operation(summary = "get pet by owner id")
    public ResponseEntity<List<PetResponseDto>> getPetByOwnerId(
            @Parameter(description = "Pet owner id", required = true)
            @RequestParam String ownerId) {
        List<PetResponseDto> petResponseDtoList = petService.getPetByOwnerId(ownerId);
        return ResponseEntity.ok(petResponseDtoList);
    }


    @GetMapping("/name/{name}")
    @Operation(summary = "Get pets by name")

    public ResponseEntity<List<PetResponseDto>> getPetsByName(@Parameter(description = "Name of the pet")
                                                                  @PathVariable String name) {
        List<PetResponseDto> pets = petService.getPetByName(name);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/breed/{breed}")
    @Operation(summary = "Get pets by breed")
    public ResponseEntity<List<PetResponseDto>> getPetsByBreed(@Parameter(description = "Breed of the pet")
                                                                   @PathVariable String breed) {
        List<PetResponseDto> pets = petService.getPetByBreed(breed);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/age/{age}")
    @Operation(summary = "Get pets by age")
    public ResponseEntity<List<PetResponseDto>> getPetsByAge(@Parameter(description = "Age of the pet")
                                                                 @PathVariable String age) {
        List<PetResponseDto> pets = petService.getPetByAge(age);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/color/{color}")
    @Operation(summary = "Get pets by color")
    public ResponseEntity<List<PetResponseDto>> getPetsByColor(@Parameter(description = "Color of the pet")
                                                                   @PathVariable String color) {
        List<PetResponseDto> pets = petService.getPetByColor(color);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/gender/{gender}")
    @Operation(summary = "Get pets by gender")
    public ResponseEntity<List<PetResponseDto>> getPetsByGender(@Parameter(description = "Gender of the pet")
                                                                    @PathVariable String gender) {
        List<PetResponseDto> pets = petService.getPetByGender(gender);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/date-between")
    @Operation(summary = "Find pets by registration date between two dates")
    public ResponseEntity<List<PetResponseDto>> findPetsByPetDateBetween(
            @Parameter(description = "Start date")
            @RequestParam LocalDateTime startDate,
            @Parameter(description = "End date")
            @RequestParam LocalDateTime endDate) {
        List<PetResponseDto> pets = petService.findPetsByPetDateBetween(startDate, endDate);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/updated-after")
    @Operation(summary = "Find pets updated after a specific date")
    public ResponseEntity<List<PetResponseDto>> findPetsByUpdatedAtAfter(
            @Parameter(description = "Date after which pets were updated")
            @RequestParam LocalDateTime date) {
        List<PetResponseDto> pets = petService.findPetsByUpdatedAtAfter(date);
        return ResponseEntity.ok(pets);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing pet")
    public ResponseEntity<PetResponseDto> updatePet(
            @Parameter(description = "ID of the pet to be updated")
            @PathVariable String id,
            @RequestBody PetRequestDto petRequestDto) {
        PetResponseDto updatedPet = petService.updatePet(id, petRequestDto);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a pet by ID")
    public ResponseEntity<Void> deletePet(@Parameter(description = "ID of the pet to be deleted")
                                              @PathVariable String id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
