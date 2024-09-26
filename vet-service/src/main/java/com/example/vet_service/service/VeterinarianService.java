package com.example.vet_service.service;

import com.example.vet_service.dto.VeterinarianRequestDto;
import com.example.vet_service.dto.VeterinarianResponseDto;
import com.example.vet_service.model.Veterinarian;
import com.example.vet_service.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;


    @Transactional
    public VeterinarianResponseDto createVeterinarian(VeterinarianRequestDto veterinarianRequestDto) {
        log.info("VeterinarianService::createVeterinarian started");

        Veterinarian veterinarian = mapToVeterinarian(veterinarianRequestDto);
        Veterinarian savedVeterinarian = veterinarianRepository.save(veterinarian);

        log.info("VeterinarianService::createVeterinarian  veterinarian :{} ," +
                "savedVeterinarian : {}", veterinarian, savedVeterinarian);

        log.info("VeterinarianService::createVeterinarian finished");
        return mapToVeterinarianResponseDto(savedVeterinarian);
    }

    public VeterinarianResponseDto getVeterinarianById(String id) {
        log.info("VeterinarianService::getVeterinarianById started");

        Veterinarian veterinarian = veterinarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found with id: " + id));

        return mapToVeterinarianResponseDto(veterinarian);
    }

    public void deleteVeterinarian(String id) {
        log.info("VeterinarianService::deleteVeterinarian started");

        Veterinarian veterinarian = veterinarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found with id: " + id));

        log.info("VeterinarianService::deleteVeterinarian  veterinarian :{} ,",veterinarian);

        veterinarianRepository.delete(veterinarian);

        log.info("VeterinarianService::deleteVeterinarian finished");
    }


    private VeterinarianResponseDto mapToVeterinarianResponseDto(Veterinarian veterinarian) {
        return VeterinarianResponseDto.builder()
                .firstName(veterinarian.getFirstName())
                .lastName(veterinarian.getLastName())
                .specialization(veterinarian.getSpecialization())
                .availability(veterinarian.getAvailability())
                .address(veterinarian.getAddress())
                .phoneNumber(veterinarian.getPhoneNumber())
                .email(veterinarian.getEmail())
                .build();
    }

    private Veterinarian mapToVeterinarian(VeterinarianRequestDto veterinarianRequestDto) {
        return Veterinarian.builder()
                .id(veterinarianRequestDto.getId())
                .firstName(veterinarianRequestDto.getFirstName())
                .lastName(veterinarianRequestDto.getLastName())
                .specialization(veterinarianRequestDto.getSpecialization())
                .availability(veterinarianRequestDto.getAvailability())
                .address(veterinarianRequestDto.getAddress())
                .phoneNumber(veterinarianRequestDto.getPhoneNumber())
                .email(veterinarianRequestDto.getEmail())
                .build();
    }


}
