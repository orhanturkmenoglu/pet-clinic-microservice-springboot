package com.example.vet_service.mapper;

import com.example.vet_service.dto.VeterinarianRequestDto;
import com.example.vet_service.dto.VeterinarianResponseDto;
import com.example.vet_service.model.Veterinarian;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class VeterinarianMapper {

    public VeterinarianResponseDto mapToVeterinarianResponseDto(Veterinarian veterinarian) {
        return VeterinarianResponseDto.builder()
                .id(veterinarian.getId())
                .firstName(veterinarian.getFirstName())
                .lastName(veterinarian.getLastName())
                .email(veterinarian.getEmail())
                .availability(veterinarian.getAvailability())
                .specialization(veterinarian.getSpecialization())
                .address(veterinarian.getAddress())
                .phoneNumber(veterinarian.getPhoneNumber())
                .veterinarianDate(veterinarian.getVeterinarianDate())
                .build();
    }

    public Veterinarian mapToVeterinarian(VeterinarianRequestDto veterinarianRequestDto) {
        return  Veterinarian.builder()
                .firstName(veterinarianRequestDto.getFirstName())
                .lastName(veterinarianRequestDto.getLastName())
                .email(veterinarianRequestDto.getEmail())
                .availability(veterinarianRequestDto.getAvailability())
                .specialization(veterinarianRequestDto.getSpecialization())
                .address(veterinarianRequestDto.getAddress())
                .phoneNumber(veterinarianRequestDto.getPhoneNumber())
                .build();
    }

    public List<VeterinarianResponseDto> mapToVeterinarianResponseDtoList(List<Veterinarian> veterinarians) {
        return veterinarians.stream()
                .map(this::mapToVeterinarianResponseDto)
                .toList();
    }


}
