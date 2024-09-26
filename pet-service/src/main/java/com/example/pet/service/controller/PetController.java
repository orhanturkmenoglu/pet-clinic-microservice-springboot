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


    @GetMapping("owner/{ownerId}")
    @Operation(summary = "get pet by owner id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet successful"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<List<PetResponseDto>> getPetByOwnerId(
            @Parameter(description = "Owner ID", required = true)
            @PathVariable String ownerId) {
        return ResponseEntity.ok(petService.getPetByOwnerId(ownerId));
    }

}
