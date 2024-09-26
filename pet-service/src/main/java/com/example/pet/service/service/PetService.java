package com.example.pet.service.service;

import com.example.pet.service.dto.OwnerResponseDto;
import com.example.pet.service.dto.PetRequestDto;
import com.example.pet.service.dto.PetResponseDto;
import com.example.pet.service.model.Pet;
import com.example.pet.service.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final PetRepository petRepository;

    private final RestTemplate restTemplate;


    @Transactional
    public PetResponseDto createPet(PetRequestDto petRequestDto) {
        Pet pet = mapToPet(petRequestDto);
        Pet savedPet = petRepository.save(pet);
        PetResponseDto petResponseDto = mapToPetResponseDto(savedPet);
        try {
            // owner-service ile iletişim kurup owner id bilgisi ile sahip bilgilerini aktaracaz.
            // exchange(): bütün http isteklerini gerçekleştirmemizi sağlar
            String url = "http://localhost:8083/api/v1/owners/"+pet.getOwnerId();
            ResponseEntity<OwnerResponseDto> owner = restTemplate.exchange(url,
                    HttpMethod.GET,
                    null,
                    OwnerResponseDto.class);

            log.info("PetResponseDto::createPet Owner by id: {}", owner);

            petResponseDto.setOwnerResponseDto(owner.getBody());

        }catch (HttpClientErrorException e) {
            // 4xx hatalarını yakalayın
            throw new RuntimeException(e.getMessage());
        } catch (HttpServerErrorException e) {
            // 5xx hatalarını yakalayın
            throw new RuntimeException(e.getMessage());
        } catch (RestClientException e) {
            // Diğer hatalar
            throw new RuntimeException(e.getMessage());
        }


        return petResponseDto;
    }


    public PetResponseDto getPetById(String id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        return mapToPetResponseDto(pet);
    }


    public List<PetResponseDto> getPetByOwnerId(String ownerId) {
        Pet pet = petRepository.findPetByOwnerId(ownerId)
                .orElseThrow(() -> new RuntimeException("owner not found with id:" + ownerId));

        ArrayList<Pet> pets = new ArrayList<>();

        pets.add(pet);

        return mapToPetResponseDtoList(pets);
    }


    private PetResponseDto mapToPetResponseDto(Pet pet) {
        return PetResponseDto.builder()
                .ownerId(pet.getOwnerId())
                .name(pet.getName())
                .type(pet.getType())
                .age(pet.getAge())
                .breed(pet.getBreed())
                .description(pet.getDescription())
                .gender(pet.getGender())
                .color(pet.getColor())
                .build();
    }

    private List<PetResponseDto> mapToPetResponseDtoList(List<Pet> pets) {
        return pets.stream()
                .map(this::mapToPetResponseDto)
                .toList();
    }

    private Pet mapToPet(PetRequestDto petRequestDto) {
        return Pet.builder()
                .ownerId(petRequestDto.getOwnerId())
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
