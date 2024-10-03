package com.example.owner_service.controller;

import com.example.owner_service.dto.OwnerRequestDto;
import com.example.owner_service.dto.OwnerResponseDto;
import com.example.owner_service.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    // Yeni bir sahip oluşturma
    @PostMapping
    @Operation(summary = "Create a new owner",
            description = "Creates a new owner and returns the created owner details.")
    public ResponseEntity<OwnerResponseDto> createOwner(
            @Parameter(description = "Owner details to create", required = true)
            @RequestBody OwnerRequestDto ownerRequest) {
        OwnerResponseDto createdOwner = ownerService.createOwner(ownerRequest);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    // Tüm sahipleri listeleme
    @Operation(summary = "Get all owners")
    @GetMapping
    public ResponseEntity<List<OwnerResponseDto>> findAll() {
        List<OwnerResponseDto> owners = ownerService.findAll();
        return ResponseEntity.ok(owners);
    }

    // Belirli bir sahibi bulma
    @GetMapping("/{id}")
    @Operation(summary = "Get owner by ID",
            description = "Retrieves the owner details by the given ID.")
    public ResponseEntity<OwnerResponseDto> getOwnerById(
            @Parameter(description = "ID of the owner to retrieve", required = true)
            @PathVariable String id) {
        OwnerResponseDto owner = ownerService.getOwnerById(id);
        if (owner != null) {
            return ResponseEntity.ok(owner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get owners by first name")
    @GetMapping("/first-name/{firstName}")
    public ResponseEntity<List<OwnerResponseDto>> getOwnersByFirstName(@Parameter(name = "firstName",
            description = "First name of the owner to search", required = true)
                                                                       @PathVariable String firstName) {
        List<OwnerResponseDto> owners = ownerService.getOwnersByFirstName(firstName);
        return ResponseEntity.ok(owners);
    }

    @Operation(summary = "Get owners by last name")
    @GetMapping("/last-name/{lastName}")
    public ResponseEntity<List<OwnerResponseDto>> getOwnersByLastName(
            @Parameter(name = "lastName", description = "Last name of the owner to search", required = true)
            @PathVariable String lastName) {
        List<OwnerResponseDto> owners = ownerService.getOwnersByLastName(lastName);
        return ResponseEntity.ok(owners);
    }

    @Operation(summary = "Get an owner by phone number")
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<OwnerResponseDto> getOwnerByPhoneNumber(
            @Parameter(name = "phoneNumber", description = "Phone number of the owner to search", required = true)
            @PathVariable String phoneNumber) {
        OwnerResponseDto ownerResponseDto = ownerService.getOwnerByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("No owner found with phone number: " + phoneNumber));
        return ResponseEntity.ok(ownerResponseDto);
    }

    @Operation(summary = "Get an owner by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<OwnerResponseDto> getOwnerByEmail(
            @Parameter(name = "email", description = "Email address of the owner to search", required = true)
            @PathVariable String email) {
        OwnerResponseDto ownerResponseDto = ownerService.getOwnerByEmail(email)
                .orElseThrow(() -> new RuntimeException("No owner found with email: " + email));
        return ResponseEntity.ok(ownerResponseDto);
    }

    @Operation(summary = "Find owners by owner date between")
    @GetMapping("/date-range")
    public ResponseEntity<List<OwnerResponseDto>> findOwnersByOwnerDateBetween(
            @Parameter(name = "startDate", description = "Start date for the search", required = true)
            @RequestParam LocalDateTime startDate,
            @Parameter(name = "endDate", description = "End date for the search", required = true)
            @RequestParam LocalDateTime endDate) {
        List<OwnerResponseDto> owners = ownerService.findOwnersByOwnerDateBetween(startDate, endDate);
        return ResponseEntity.ok(owners);
    }

    @Operation(summary = "Find owners updated after a specific date")
    @GetMapping("/updated-after")
    public ResponseEntity<List<OwnerResponseDto>> findOwnersByUpdatedAtAfter(
            @Parameter(name = "date", description = "Date to search for updated owners", required = true)
            @RequestParam LocalDateTime date) {
        List<OwnerResponseDto> owners = ownerService.findOwnersByUpdatedAtAfter(date);
        return ResponseEntity.ok(owners);
    }


    // Sahibi güncelleme
    @PutMapping("/{id}")
    @Operation(summary = "Update owner",
            description = "Updates the details of an existing owner.")
    public ResponseEntity<OwnerResponseDto> updateOwner(
            @Parameter(description = "ID of the owner to update", required = true)
            @PathVariable String id,
            @Parameter(description = "Updated owner details", required = true)
            @RequestBody OwnerRequestDto ownerRequest) {
        OwnerResponseDto updatedOwner = ownerService.updateOwner(id, ownerRequest);
        if (updatedOwner != null) {
            return ResponseEntity.ok(updatedOwner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Sahibi silme
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete owner",
            description = "Deletes the owner by the given ID.")
    public ResponseEntity<Void> deleteOwner(
            @Parameter(description = "ID of the owner to delete", required = true)
            @PathVariable String id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}
