package com.example.owner_service.controller;

import com.example.owner_service.dto.OwnerRequestDto;
import com.example.owner_service.dto.OwnerResponseDto;
import com.example.owner_service.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    // Yeni bir sahip oluşturma
    @PostMapping
    @Operation(summary = "Create a new owner", description = "Creates a new owner and returns the created owner details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<OwnerResponseDto> createOwner(
            @Parameter(description = "Owner details to create", required = true)
            @RequestBody OwnerRequestDto ownerRequest) {
        OwnerResponseDto createdOwner = ownerService.createOwner(ownerRequest);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    // Belirli bir sahibi bulma
    @GetMapping("/{id}")
    @Operation(summary = "Get owner by ID", description = "Retrieves the owner details by the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner found"),
            @ApiResponse(responseCode = "404", description = "Owner not found")
    })
    public ResponseEntity<OwnerResponseDto> getOwnerById(
            @Parameter(description = "ID of the owner to retrieve", required = true)
            @PathVariable String id) {
        OwnerResponseDto owner = ownerService.findOwnerById(id);
        if (owner != null) {
            return ResponseEntity.ok(owner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Sahibi güncelleme
    @PutMapping("/{id}")
    @Operation(summary = "Update owner", description = "Updates the details of an existing owner.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner updated successfully"),
            @ApiResponse(responseCode = "404", description = "Owner not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
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
    @Operation(summary = "Delete owner", description = "Deletes the owner by the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Owner deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Owner not found")
    })
    public ResponseEntity<Void> deleteOwner(
            @Parameter(description = "ID of the owner to delete", required = true)
            @PathVariable String id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}
