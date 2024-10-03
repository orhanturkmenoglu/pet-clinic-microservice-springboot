package com.example.vet_service.controller;

import com.example.vet_service.dto.VeterinarianRequestDto;
import com.example.vet_service.dto.VeterinarianResponseDto;
import com.example.vet_service.service.VeterinarianService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/veterinarian")
@RequiredArgsConstructor
@Tag(name = "Veterinarian API", description = "Veterinarian management operations")
public class VeterinarianController {

    private final VeterinarianService veterinarianService;

    @Operation(summary = "Create a new veterinarian",
            description = "Creates a new veterinarian with the provided details.")
    @PostMapping
    public ResponseEntity<VeterinarianResponseDto> createVeterinarian(
            @Valid
            @Parameter(description = "Veterinarian creation details", required = true)
            @RequestBody VeterinarianRequestDto veterinarianRequestDto) {
        VeterinarianResponseDto veterinarianResponseDto = veterinarianService.createVeterinarian(veterinarianRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(veterinarianResponseDto);
    }

    @Operation(summary = "Get veterinarians all",
            description = "Retrieves  veterinarian's details")

    @GetMapping
    public ResponseEntity<List<VeterinarianResponseDto>> getAllVeterinarians()
    {
        List<VeterinarianResponseDto> responseDtoList =
                veterinarianService.getAllVeterinarians();

        return ResponseEntity.ok(responseDtoList);
    }

    @Operation(summary = "Get veterinarian by ID",
            description = "Retrieves a veterinarian's details by their ID.")

    @GetMapping({"/{id}"})
    public ResponseEntity<VeterinarianResponseDto> getVeterinarianById(
            @Parameter(description = "ID of the veterinarian to be retrieved", required = true)
            @PathVariable("id") String id) {
        VeterinarianResponseDto veterinarianResponseDto = veterinarianService.getVeterinarianById(id);
        return ResponseEntity.ok(veterinarianResponseDto);
    }


    @Operation(summary = "Get veterinarian by ID",
            description = "Retrieves a veterinarian's details by their ID.")

    @GetMapping({"/specialization"})
    public ResponseEntity<List<VeterinarianResponseDto>> getVeterinarianBySpecialization(
            @Parameter(description = "specialization of the veterinarian to be retrieved", required = true)
            @RequestParam("specialization") String specialization)
    {
        List<VeterinarianResponseDto> veterinarianResponseDtoList = veterinarianService.getVeterinarianBySpecialization(specialization);
        return ResponseEntity.ok(veterinarianResponseDtoList);
    }


    @Operation(summary = "Get veterinarians by availability",
            description = "Returns a list of veterinarians matching the availability.")
    @GetMapping("/availability")
    public ResponseEntity<List<VeterinarianResponseDto>> getVeterinariansByAvailability(
            @Parameter(description = "Availability status")
            @RequestParam String availability) {
        List<VeterinarianResponseDto> veterinarians =
                veterinarianService.getVeterinariansByAvailability(availability);

        return ResponseEntity.ok(veterinarians);
    }

    @Operation(summary = "Get veterinarians by name",
            description = "Returns a list of veterinarians matching the name.")
    @GetMapping("/name")
    public ResponseEntity<List<VeterinarianResponseDto>> getVeterinariansByName(
            @Parameter(description = "First name")
            @RequestParam(required = false) String firstName,
            @Parameter(description = "Last name")
            @RequestParam(required = false) String lastName) {

        List<VeterinarianResponseDto> veterinarians =
                veterinarianService.getVeterinariansByName(firstName, lastName);

        return ResponseEntity.ok(veterinarians);
    }


    @Operation(summary = "Get veterinarians by phone number",
            description = "Returns a list of veterinarians matching the phone number.")
    @GetMapping("/phone")
    public ResponseEntity<List<VeterinarianResponseDto>> getVeterinariansByPhoneNumber(
            @Parameter(description = "Phone number")
            @RequestParam String phoneNumber) {

        List<VeterinarianResponseDto> veterinarians =
                veterinarianService.getVeterinariansByPhoneNumber(phoneNumber);

        return ResponseEntity.ok(veterinarians);
    }

    @Operation(summary = "Get veterinarians by specialization and availability",
            description = "Returns a list of veterinarians matching the specialization and availability.")
    @GetMapping("/specialization-availability")
    public ResponseEntity<List<VeterinarianResponseDto>> getVeterinariansBySpecializationAndAvailability(
            @Parameter(description = "Specialization")
            @RequestParam String specialization,
            @Parameter(description = "Availability")
            @RequestParam String availability) {

        List<VeterinarianResponseDto> veterinarians =
                veterinarianService.getVeterinariansBySpecializationAndAvailability(specialization, availability);

        return ResponseEntity.ok(veterinarians);
    }

    @Operation(summary = "Get veterinarians by address",
            description = "Returns a list of veterinarians matching the address.")
    @GetMapping("/address")
    public ResponseEntity<List<VeterinarianResponseDto>> getVeterinariansByAddress(
            @Parameter(description = "Address")
            @RequestParam String address) {

        List<VeterinarianResponseDto> veterinarians =
                veterinarianService.getVeterinariansByAddress(address);

        return ResponseEntity.ok(veterinarians);
    }


    @Operation(summary = "Get veterinarians by date range",
            description = "Returns a list of veterinarians registered within the specified date range.")
    @GetMapping("/date-range")
    public ResponseEntity<List<VeterinarianResponseDto>> getVeterinariansByDateRange(
            @Parameter(description = "Start date")
            @RequestParam LocalDateTime startDate,
            @Parameter(description = "End date")
            @RequestParam LocalDateTime endDate) {

        List<VeterinarianResponseDto> veterinarians =
                veterinarianService.getVeterinariansByDateRange(startDate, endDate);

        return ResponseEntity.ok(veterinarians);
    }

    @Operation(summary = "Update a veterinarian",
            description = "Updates the veterinarian with the specified ID.")
    @PutMapping("/{id}")
    public ResponseEntity<VeterinarianResponseDto> updateVeterinarian(
            @Parameter(description = "Veterinarian ID")
            @PathVariable String id,
            @Parameter(description = "Veterinarian data")
            @RequestBody VeterinarianRequestDto veterinarianRequestDto) {

        VeterinarianResponseDto updatedVeterinarian =
                veterinarianService.updateVeterinarian(id, veterinarianRequestDto);

        return ResponseEntity.ok(updatedVeterinarian);
    }

    @Operation(summary = "Delete veterinarian by ID",
            description = "Deletes a veterinarian by their ID.")

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteVeterinarian(
            @Parameter(description = "ID of the veterinarian to be deleted", required = true)
            @PathVariable("id") String id) {
        veterinarianService.deleteVeterinarian(id);
        return ResponseEntity.noContent().build();
    }
}