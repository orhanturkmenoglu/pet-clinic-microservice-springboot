package com.example.vet_service.controller;

import com.example.vet_service.dto.VeterinarianRequestDto;
import com.example.vet_service.dto.VeterinarianResponseDto;
import com.example.vet_service.service.VeterinarianService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/veterinarian")
@RequiredArgsConstructor
@Tag(name = "Veterinarian API", description = "Veterinarian management operations")
public class VeterinarianController {

    private final VeterinarianService veterinarianService;

    @Operation(summary = "Create a new veterinarian", description = "Creates a new veterinarian with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veterinarian created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<VeterinarianResponseDto> createVeterinarian(
            @Parameter(description = "Veterinarian creation details", required = true)
            @RequestBody VeterinarianRequestDto veterinarianRequestDto) {
        VeterinarianResponseDto veterinarianResponseDto = veterinarianService.createVeterinarian(veterinarianRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(veterinarianResponseDto);
    }

    @Operation(summary = "Get veterinarians all", description = "Retrieves  veterinarian's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veterinarian retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Veterinarian not found")
    })
    @GetMapping
    public ResponseEntity<List<VeterinarianResponseDto>> getAllVeterinarians()
    {
        List<VeterinarianResponseDto> responseDtoList =
                veterinarianService.getAllVeterinarians();

        return ResponseEntity.ok(responseDtoList);
    }

    @Operation(summary = "Get veterinarian by ID", description = "Retrieves a veterinarian's details by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veterinarian retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Veterinarian not found")
    })
    @GetMapping({"/{id}"})
    public ResponseEntity<VeterinarianResponseDto> getVeterinarianById(
            @Parameter(description = "ID of the veterinarian to be retrieved", required = true)
            @PathVariable("id") String id) {
        VeterinarianResponseDto veterinarianResponseDto = veterinarianService.getVeterinarianById(id);
        return ResponseEntity.ok(veterinarianResponseDto);
    }

    @Operation(summary = "Delete veterinarian by ID", description = "Deletes a veterinarian by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Veterinarian deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Veterinarian not found")
    })
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteVeterinarian(
            @Parameter(description = "ID of the veterinarian to be deleted", required = true)
            @PathVariable("id") String id) {
        veterinarianService.deleteVeterinarian(id);
        return ResponseEntity.noContent().build();
    }
}