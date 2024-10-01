package com.example.pet.service.service;

import com.example.pet.service.dto.OwnerResponseDto;
import com.example.pet.service.dto.PetRequestDto;
import com.example.pet.service.dto.PetResponseDto;
import com.example.pet.service.mapper.PetMapper;
import com.example.pet.service.model.Pet;
import com.example.pet.service.repository.PetRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
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

    private final PetMapper petMapper;

    int retryCount = 1;

    @Transactional
    @RateLimiter(name = "ownerRateLimiter", fallbackMethod = "ownerFallback")
    @CircuitBreaker(name = "ownerBreaker", fallbackMethod = "ownerFallback") // devre kesici.
    @Retry(name = "ownerRetry", fallbackMethod = "ownerFallback") // tekrar deneme mekanizması
   // üçünüde aynı anda kullanırsak ilk devreye girecek ratelimiter,sonra circuitbreaker,retry şeklinde çalışacak.
    public PetResponseDto createPet(PetRequestDto petRequestDto) {
        log.info("PetResponseDto::createPet started");

        log.info("retry count : {}", retryCount);
        retryCount++;

        Pet pet = petMapper.mapToPet(petRequestDto);
        Pet savedPet = petRepository.save(pet);
        PetResponseDto petResponseDto = petMapper.mapToPetResponseDto(savedPet);
        try {
            // owner-service ile iletişim kurup owner id bilgisi ile sahip bilgilerini aktaracaz.
            // exchange(): bütün http isteklerini gerçekleştirmemizi sağlar
            String url = "http://localhost:8083/api/v1/owners/" + pet.getOwnerId();
            ResponseEntity<OwnerResponseDto> owner = restTemplate.exchange(url,
                    HttpMethod.GET,
                    null,
                    OwnerResponseDto.class);

            log.info("PetResponseDto::createPet Owner by id: {}", owner);

            petResponseDto.setOwnerResponseDto(owner.getBody());

        } catch (HttpClientErrorException e) {
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


    // creating fall back method for circuit breaker
    public PetResponseDto ownerFallback(Exception e) {
        log.error("Fallback method called: " + e.getMessage());
        return PetResponseDto.builder().build();
    }

    public List<PetResponseDto> getAllPets() {
        log.info("PetResponseDto::getAllPets started");

        List<Pet> pets = petRepository.findAll();
        log.info("PetResponseDto::getAllPets pets : {}", pets);

        log.info("PetResponseDto::getAllPets finished");
        return petMapper.mapToPetResponseDtoList(pets);
    }


    public PetResponseDto getPetById(String id) {
        log.info("PetResponseDto::getPetById started");

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        log.info("PetResponseDto::getPetById pet : {}", pet);

        log.info("PetResponseDto::getPetById finished");
        return petMapper.mapToPetResponseDto(pet);
    }

    public List<PetResponseDto> getPetByType(String petType) {
        log.info("PetResponseDto::getPetByType started");

        // stream kullanarak filtreleme yapalım
        List<Pet> pets = petRepository.findAll()
                .stream()
                .filter(pet -> pet.getType().equalsIgnoreCase(petType))
                .toList();
        log.info("PetResponseDto::getPetByType pets :{}", pets);

        log.info("PetResponseDto::getPetByType finished");
        return petMapper.mapToPetResponseDtoList(pets);
    }


    public List<PetResponseDto> getPetByOwnerId(String ownerId) {
        log.info("PetResponseDto::getPetByOwnerId started");

        Pet pet = petRepository.findPetByOwnerId(ownerId)
                .orElseThrow(() -> new RuntimeException("owner not found with id:" + ownerId));
        log.info("PetResponseDto::getPetByOwnerId pet : {}", pet);

        ArrayList<Pet> pets = new ArrayList<>();
        pets.add(pet);

        log.info("PetResponseDto::getPetByOwnerId pets : {}", pets);

        log.info("PetResponseDto::getPetByOwnerId finished");
        return petMapper.mapToPetResponseDtoList(pets);
    }
}
