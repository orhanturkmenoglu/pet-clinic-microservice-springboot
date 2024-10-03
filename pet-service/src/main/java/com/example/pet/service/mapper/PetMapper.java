package com.example.pet.service.mapper;

import com.example.pet.service.dto.PetRequestDto;
import com.example.pet.service.dto.PetResponseDto;
import com.example.pet.service.model.Pet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetMapper {

    public PetResponseDto mapToPetResponseDto(Pet pet){
        return PetResponseDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .age(pet.getAge())
                .breed(pet.getBreed())
                .color(pet.getColor())
                .ownerId(pet.getOwnerId())
                .description(pet.getDescription())
                .type(pet.getType())
                .gender(pet.getGender())
                .petDate(pet.getPetDate())
                .updatedAt(pet.getUpdatedAt())
                .build();
    }

    public  Pet mapToPet(PetRequestDto petRequestDto){
        return Pet.builder()
                .name(petRequestDto.getName())
                .age(petRequestDto.getAge())
                .breed(petRequestDto.getBreed())
                .color(petRequestDto.getColor())
                .ownerId(petRequestDto.getOwnerId())
                .description(petRequestDto.getDescription())
                .type(petRequestDto.getType())
                .gender(petRequestDto.getGender())
                .build();
    }

    public List<PetResponseDto> mapToPetResponseDtoList(List<Pet> pets){
        return pets.stream()
                .map(this::mapToPetResponseDto)
                .toList();
    }
}
