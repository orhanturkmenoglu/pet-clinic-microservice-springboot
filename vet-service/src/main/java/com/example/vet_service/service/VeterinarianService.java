package com.example.vet_service.service;

import com.example.vet_service.dto.VeterinarianRequestDto;
import com.example.vet_service.dto.VeterinarianResponseDto;
import com.example.vet_service.mapper.VeterinarianMapper;
import com.example.vet_service.model.Veterinarian;
import com.example.vet_service.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;

    private final VeterinarianMapper veterinarianMapper;

    @Transactional
    public VeterinarianResponseDto createVeterinarian(VeterinarianRequestDto veterinarianRequestDto) {
        log.info("VeterinarianService::createVeterinarian started");

        Veterinarian veterinarian = veterinarianMapper.mapToVeterinarian(veterinarianRequestDto);
        Veterinarian savedVeterinarian = veterinarianRepository.save(veterinarian);


        log.info("VeterinarianService::createVeterinarian  veterinarian :{} ," +
                "savedVeterinarian : {}", veterinarian, savedVeterinarian);

        log.info("VeterinarianService::createVeterinarian finished");
        return veterinarianMapper.mapToVeterinarianResponseDto(savedVeterinarian);
    }

    public List<VeterinarianResponseDto> getAllVeterinarians() {
        log.info("VeterinarianService::getAllVeterinarians started");

        List<Veterinarian> veterinarians = veterinarianRepository.findAll();

        log.info("VeterinarianService::getAllVeterinarians finished");
        return veterinarianMapper.mapToVeterinarianResponseDtoList(veterinarians);
    }

    public VeterinarianResponseDto getVeterinarianById(String id) {
        log.info("VeterinarianService::getVeterinarianById started");

        Veterinarian veterinarian = veterinarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found with id: " + id));

        return veterinarianMapper.mapToVeterinarianResponseDto(veterinarian);
    }

    public List<VeterinarianResponseDto> getVeterinarianBySpecialization(String  firstName){
        log.info("VeterinarianService::getVeterinarianBySpecialization started");

        List<Veterinarian> veterinarians = veterinarianRepository
                .findBySpecializationIsLikeIgnoreCase(firstName);

        log.info("VeterinarianService::getVeterinarianBySpecialization  veterinarians :{} ,"
                ,veterinarians);

        return veterinarianMapper.mapToVeterinarianResponseDtoList(veterinarians);
    }

    public void deleteVeterinarian(String id) {
        log.info("VeterinarianService::deleteVeterinarian started");

        Veterinarian veterinarian = veterinarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found with id: " + id));

        log.info("VeterinarianService::deleteVeterinarian  veterinarian :{} ,", veterinarian);

        veterinarianRepository.delete(veterinarian);

        log.info("VeterinarianService::deleteVeterinarian finished");
    }


}
