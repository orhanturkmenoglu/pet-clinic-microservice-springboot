package com.example.vet_service.mapper;

import com.example.vet_service.dto.VeterinarianRequestDto;
import com.example.vet_service.dto.VeterinarianResponseDto;
import com.example.vet_service.model.Veterinarian;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VeterinarianMapper {

    VeterinarianMapper INSTANCE = Mappers.getMapper(VeterinarianMapper.class);

    VeterinarianResponseDto mapToVeterinarianResponseDto(Veterinarian veterinarian);

    Veterinarian mapToVeterinarian(VeterinarianRequestDto veterinarianRequestDto);

    List<VeterinarianResponseDto> mapToVeterinarianResponseDtoList(List<Veterinarian> veterinarians);


}
