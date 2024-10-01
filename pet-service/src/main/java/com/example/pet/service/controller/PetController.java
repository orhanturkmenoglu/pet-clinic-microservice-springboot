package com.example.pet.service.controller;

import com.example.pet.service.dto.PetRequestDto;
import com.example.pet.service.dto.PetResponseDto;
import com.example.pet.service.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    @Operation(summary = "Create a new pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pet created successful"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<PetResponseDto> createPet(
            @Parameter(description = "Pet request DTO containing pet details", required = true)
            @RequestBody PetRequestDto petRequestDto) {
        PetResponseDto petResponseDto = petService.createPet(petRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(petResponseDto);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all pets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet successful"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<List<PetResponseDto>> getAllPets() {
        List<PetResponseDto> petResponseDtoList = petService.getAllPets();
        return ResponseEntity.ok(petResponseDtoList);
    }


    @GetMapping("/petType")
    @Operation(summary = "get all pets by type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet successful"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<List<PetResponseDto>> getPetByType(
            @Parameter(description = "PetType", required = true)
            @RequestParam String petType) {
        List<PetResponseDto> petResponseDtoList = petService.getPetByType(petType);
        return ResponseEntity.ok(petResponseDtoList);
    }

    @GetMapping("/petId")
    @Operation(summary = "get pet by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet successful"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<PetResponseDto> getPetById(
            @Parameter(description = "Pet id", required = true)
            @RequestParam String petId) {
        PetResponseDto petResponseDtoList = petService.getPetById(petId);
        return ResponseEntity.ok(petResponseDtoList);
    }

    @GetMapping("/ownerId")
    @Operation(summary = "get pet by owner id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet successful"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<List<PetResponseDto>> getPetByOwnerId(
            @Parameter(description = "Pet owner id", required = true)
            @RequestParam String ownerId) {
        List<PetResponseDto> petResponseDtoList = petService.getPetByOwnerId(ownerId);
        return ResponseEntity.ok(petResponseDtoList);
    }

}
