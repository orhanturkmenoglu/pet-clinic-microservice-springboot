package com.example.pet.service.service;

import com.example.pet.service.dto.PetRequestDto;
import com.example.pet.service.dto.PetResponseDto;
import com.example.pet.service.model.Pet;
import com.example.pet.service.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;


    @Transactional
    public PetResponseDto createPet(PetRequestDto petRequestDto) {
        Pet pet = mapToPet(petRequestDto);
        Pet savedPet = petRepository.save(pet);

        return mapToPetResponseDto(savedPet);
    }

    private PetResponseDto mapToPetResponseDto(Pet pet) {
        return PetResponseDto.builder()
                .name(pet.getName())
                .type(pet.getType())
                .age(pet.getAge())
                .breed(pet.getBreed())
                .description(pet.getDescription())
                .gender(pet.getGender())
                .color(pet.getColor())
                .build();
    }

    private Pet mapToPet(PetRequestDto petRequestDto) {
        return Pet.builder()
                .name(petRequestDto.getName())
                .type(petRequestDto.getType())
                .age(petRequestDto.getAge())
                .breed(petRequestDto.getBreed())
                .description(petRequestDto.getDescription())
                .gender(petRequestDto.getGender())
                .color(petRequestDto.getColor())
                .build();
    }
}
