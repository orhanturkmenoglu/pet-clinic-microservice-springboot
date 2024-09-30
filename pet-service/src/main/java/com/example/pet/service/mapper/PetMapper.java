package com.example.pet.service.mapper;

import com.example.pet.service.dto.PetRequestDto;
import com.example.pet.service.dto.PetResponseDto;
import com.example.pet.service.model.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PetMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    PetResponseDto mapToPetResponseDto(Pet pet);

    Pet mapToPet(PetRequestDto petRequestDto);

    List<PetResponseDto> mapToPetResponseDtoList(List<Pet> pets);
}
