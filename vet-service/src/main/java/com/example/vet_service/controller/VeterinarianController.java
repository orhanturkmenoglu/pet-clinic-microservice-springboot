package com.example.vet_service.controller;

import com.example.vet_service.dto.VeterinarianRequestDto;
import com.example.vet_service.dto.VeterinarianResponseDto;
import com.example.vet_service.service.VeterinarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/veterinarian")
@RequiredArgsConstructor
public class VeterinarianController {

    private final VeterinarianService veterinarianService;

    @PostMapping
    public ResponseEntity<VeterinarianResponseDto> createVeterinarian(@RequestBody VeterinarianRequestDto veterinarianRequestDto) {
        VeterinarianResponseDto veterinarianResponseDto = veterinarianService.createVeterinarian(veterinarianRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(veterinarianResponseDto);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<VeterinarianResponseDto> getVeterinarianById(@PathVariable("id") String id) {
        VeterinarianResponseDto veterinarianResponseDto = veterinarianService.getVeterinarianById(id);
        return ResponseEntity.ok(veterinarianResponseDto);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteVeterinarian(@PathVariable("id") String id) {
        veterinarianService.deleteVeterinarian(id);
        return ResponseEntity.noContent().build();
    }

}
