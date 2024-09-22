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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    @Operation(summary = "Create a new pet")
    @ApiResponses(value ={
            @ApiResponse(responseCode ="201",description ="Pet created successful"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<PetResponseDto> createPet(
            @Parameter(description = "Pet request DTO containing pet details",required = true)
            @RequestBody PetRequestDto petRequestDto) {
        PetResponseDto petResponseDto = petService.createPet(petRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(petResponseDto);
    }
}
