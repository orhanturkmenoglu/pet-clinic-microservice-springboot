package com.example.owner_service.controller;

import com.example.owner_service.dto.OwnerRequestDto;
import com.example.owner_service.dto.OwnerResponseDto;
import com.example.owner_service.service.OwnerService;
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
    public ResponseEntity<OwnerResponseDto> createOwner(@RequestBody OwnerRequestDto ownerRequest) {
        OwnerResponseDto createdOwner = ownerService.createOwner(ownerRequest);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    // Belirli bir sahibi bulma
    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> getOwnerById(@PathVariable String id) {
        OwnerResponseDto owner = ownerService.findOwnerById(id);
        if (owner != null) {
            return ResponseEntity.ok(owner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Sahibi güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> updateOwner(@PathVariable String id, @RequestBody OwnerRequestDto ownerRequest) {
        OwnerResponseDto updatedOwner = ownerService.updateOwner(id, ownerRequest);
        if (updatedOwner != null) {
            return ResponseEntity.ok(updatedOwner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Sahibi silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable String id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}
