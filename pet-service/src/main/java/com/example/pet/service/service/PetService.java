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

import java.time.LocalDateTime;
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
    private PetResponseDto ownerFallback(Exception e) {
        log.error("Fallback method called: " + e.getMessage());
        return PetResponseDto.builder().build();
    }

    public List<PetResponseDto> getAllPets() {
        log.info("PetResponseDto::getAllPets started");

        List<Pet> pets = petRepository.findAll();
        log.info("PetResponseDto::getAllPets pets : {}", pets);

        if (pets.isEmpty()){
            throw new RuntimeException("Pets not found");
        }

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

    public List<PetResponseDto> getPetByName(String name) {
        log.info("PetResponseDto::getPetByName started");

        List<Pet> pets = petRepository.findByNameContainingIgnoreCase(name);

        if (pets.isEmpty()){
            throw new RuntimeException("Pets not found");
        }

        log.info("PetResponseDto::getPetByName pets : {}", pets);

        log.info("PetResponseDto::getPetByName finished");
        return petMapper.mapToPetResponseDtoList(pets);
    }

    public List<PetResponseDto> getPetByBreed(String breed) {
        log.info("PetResponseDto::getPetByBreed started");

        List<Pet> pets = petRepository.findByBreed(breed);

        log.info("PetResponseDto::getPetByBreed pets : {}", pets);
        if (pets.isEmpty()){
            throw new RuntimeException("Pets not found");
        }

        log.info("PetResponseDto::getPetByBreed finished");
        return petMapper.mapToPetResponseDtoList(pets);
    }

    public List<PetResponseDto> getPetByAge(String age) {
        log.info("PetResponseDto::getPetByAge started");

        List<Pet> pets = petRepository.findByAge(age);
        log.info("PetResponseDto::getPetByAge pets : {}", pets);

        if (pets.isEmpty()){
            throw new RuntimeException("Pets not found");
        }


        log.info("PetResponseDto::getPetByAge finished");
        return petMapper.mapToPetResponseDtoList(pets);
    }

    public List<PetResponseDto> getPetByColor(String color) {
        log.info("PetResponseDto::getPetByColor started");

        List<Pet> pets = petRepository.findByColor(color);
        log.info("PetResponseDto::getPetByColor pets : {}", pets);

        if (pets.isEmpty()){
            throw new RuntimeException("Pets not found");
        }

        log.info("PetResponseDto::getPetByColor finished");
        return petMapper.mapToPetResponseDtoList(pets);
    }

    public List<PetResponseDto> getPetByGender(String gender) {
        log.info("PetResponseDto::getPetByGender started");

        List<Pet> pets = petRepository.findByGender(gender);
        log.info("PetResponseDto::getPetByGender pets : {}", pets);

        if (pets.isEmpty()){
            throw new RuntimeException("Pets not found");
        }

        log.info("PetResponseDto::getPetByGender finished");
        return petMapper.mapToPetResponseDtoList(pets);
    }

    // Belirli bir tarih aralığında kaydedilen evcil hayvanları bul
    public List<PetResponseDto> findPetsByPetDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("PetService::findPetsByPetDateBetween started from {} to {}", startDate, endDate);

        List<Pet> pets = petRepository.findByPetDateBetween(startDate, endDate);
        log.info("PetService::findPetsByPetDateBetween pets: {}", pets);

        if (pets.isEmpty()) {
            throw new RuntimeException("No pets found between " + startDate + " and " + endDate);
        }


        log.info("PetService::findPetsByPetDateBetween finished");
        return petMapper.mapToPetResponseDtoList(pets);
    }

    // Güncellenme tarihine göre evcil hayvanları bul
    public List<PetResponseDto> findPetsByUpdatedAtAfter(LocalDateTime date) {
        log.info("PetService::findPetsByUpdatedAtAfter started for date: {}", date);

        List<Pet> pets = petRepository.findByUpdatedAtAfter(date);
        log.info("PetService::findPetsByUpdatedAtAfter pets: {}", pets);

        if (pets.isEmpty()) {
            throw new RuntimeException("No pets found after " + date);
        }


        log.info("PetService::findPetsByUpdatedAtAfter finished");
        return petMapper.mapToPetResponseDtoList(pets);
    }


    @Transactional
    public PetResponseDto updatePet(String id, PetRequestDto petRequestDto) {
        log.info("PetService::updatePet started for id: {}", id);

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));

        log.info("PetService::updatePet pet: {}", pet);

        // DTO'dan alınan bilgilerle pet'in alanlarını güncelle
        pet.setOwnerId(pet.getOwnerId());
        pet.setName(petRequestDto.getName());
        pet.setType(petRequestDto.getType());
        pet.setBreed(petRequestDto.getBreed());
        pet.setAge(petRequestDto.getAge());
        pet.setGender(petRequestDto.getGender());
        pet.setColor(petRequestDto.getColor());
        pet.setWeight(petRequestDto.getWeight());
        pet.setDescription(petRequestDto.getDescription());
        pet.setUpdatedAt(LocalDateTime.now()); // Güncellenme tarihini ayarla

        Pet updatedPet = petRepository.save(pet);
        log.info("PetService::updatePet updated pet: {}", updatedPet);

        log.info("PetService::updatePet finished");
        return petMapper.mapToPetResponseDto(updatedPet);
    }


    public void deletePet(String id) {
        log.info("PetService::deletePet started for id: {}", id);

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));

        petRepository.delete(pet);
        log.info("PetService::deletePet finished");
    }


}
