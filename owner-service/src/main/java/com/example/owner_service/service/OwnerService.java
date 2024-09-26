package com.example.owner_service.service;

import com.example.owner_service.dto.OwnerRequestDto;
import com.example.owner_service.dto.OwnerResponseDto;
import com.example.owner_service.model.Pet;
import com.example.owner_service.model.Owner;
import com.example.owner_service.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerService {

    private final OwnerRepository ownerRepository;

    private final RestTemplate restTemplate;


    // Sahip oluşturma
    public OwnerResponseDto createOwner(OwnerRequestDto ownerRequestDto) {
        Owner owner = mapToOwner(ownerRequestDto);

        Owner savedOwner = ownerRepository.save(owner);

        return mapToOwnerResponseDto(savedOwner);
    }



    // Sahibi bulma
    public OwnerResponseDto findOwnerById(String id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        // burada petservice ile iletişime geçilerek sahibe ait hayvan bilgileri getirilecek.
        // http://localhost:8080/api/v1/pets/{ownerId}
        String  url = "http://localhost:8080/api/v1/pets/owner/";
        List<Pet> petList = restTemplate.getForObject(url+owner.getId(), ArrayList.class);

        log.info("OwnerResponseDto::findOwnerById Pet list: {}", petList);

        OwnerResponseDto ownerResponseDto = mapToOwnerResponseDto(owner);
        ownerResponseDto.setPets(petList);

        log.info("OwnerResponseDto::findOwnerById Owner: {}", ownerResponseDto);
        return ownerResponseDto;
    }

    // Sahibi güncelleme
    public OwnerResponseDto updateOwner(String id, OwnerRequestDto ownerRequest) {
        Optional<Owner> ownerOpt = ownerRepository.findById(id);
        if (ownerOpt.isPresent()) {
            Owner owner = ownerOpt.get();
            owner.setFirstName(ownerRequest.getFirstName());
            owner.setLastName(ownerRequest.getLastName());
            owner.setPhoneNumber(ownerRequest.getPhoneNumber());
            owner.setEmail(ownerRequest.getEmail());
            owner.setAddress(ownerRequest.getAddress());

            Owner updatedOwner = ownerRepository.save(owner);
            return mapToOwnerResponseDto(updatedOwner);
        }
        return null; // Veya özel bir hata fırlatabilirsiniz
    }

    // Sahibi silme
    public void deleteOwner(String id) {
        ownerRepository.deleteById(id);
    }


    private OwnerResponseDto mapToOwnerResponseDto(Owner owner) {
        return OwnerResponseDto.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .phoneNumber(owner.getPhoneNumber())
                .email(owner.getEmail())
                .address(owner.getAddress())
                .build();
    }

    private Owner mapToOwner(OwnerRequestDto ownerRequestDto) {
        return Owner.builder()
                .firstName(ownerRequestDto.getFirstName())
                .lastName(ownerRequestDto.getLastName())
                .phoneNumber(ownerRequestDto.getPhoneNumber())
                .email(ownerRequestDto.getEmail())
                .address(ownerRequestDto.getAddress())
                .build();
    }

}
