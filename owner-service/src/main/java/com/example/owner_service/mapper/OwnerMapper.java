package com.example.owner_service.mapper;

import com.example.owner_service.dto.OwnerRequestDto;
import com.example.owner_service.dto.OwnerResponseDto;
import com.example.owner_service.model.Owner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OwnerMapper {

    public Owner mapToOwner(OwnerRequestDto ownerRequestDto) {
        return Owner.builder()
                .firstName(ownerRequestDto.getFirstName())
                .lastName(ownerRequestDto.getLastName())
                .email(ownerRequestDto.getEmail())
                .address(ownerRequestDto.getAddress())
                .phoneNumber(ownerRequestDto.getPhoneNumber())
                .ownerDate(LocalDateTime.now())
                .build();

    }


    public OwnerResponseDto mapToOwnerResponseDto(Owner owner) {
        return OwnerResponseDto.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .email(owner.getEmail())
                .address(owner.getAddress())
                .phoneNumber(owner.getPhoneNumber())
                .ownerDate(owner.getOwnerDate())
                .build();
    }


    public List<OwnerResponseDto> mapToOwnerResponseDtoList(List<Owner> owners) {
        return owners.stream()
                .map(this::mapToOwnerResponseDto)
                .toList();
    }
}
