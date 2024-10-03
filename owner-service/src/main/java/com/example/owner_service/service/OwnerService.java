package com.example.owner_service.service;

import com.example.owner_service.dto.OwnerRequestDto;
import com.example.owner_service.dto.OwnerResponseDto;
import com.example.owner_service.mapper.OwnerMapper;
import com.example.owner_service.model.Owner;
import com.example.owner_service.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerService {

    private final OwnerRepository ownerRepository;

    private final OwnerMapper ownerMapper ;


    // Sahip oluşturma
    @Transactional
    public OwnerResponseDto createOwner(OwnerRequestDto ownerRequestDto) {
        Owner owner = ownerMapper.mapToOwner(ownerRequestDto);

        System.out.println("Owner: " + owner);

        Owner savedOwner = ownerRepository.save(owner);

        System.out.println("savedOwner: " + savedOwner);

        return ownerMapper.mapToOwnerResponseDto(savedOwner);
    }

    // Sahipleri listeleme
    public List<OwnerResponseDto> findAll() {
        log.info("OwnerService::findAll started");

        List<Owner> owners = ownerRepository.findAll();
        log.info("Found {} owners", owners.size());

        if (owners.isEmpty()) {
            throw new RuntimeException("No owners found");
        }

        log.info("OwnerService::findAll finished");
        return ownerMapper.mapToOwnerResponseDtoList(owners);
    }


    // Sahibi bulma
    public OwnerResponseDto getOwnerById(String id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        OwnerResponseDto ownerResponseDto = ownerMapper.mapToOwnerResponseDto(owner);

        log.info("OwnerResponseDto::findOwnerById Owner: {}", ownerResponseDto);
        return ownerResponseDto;
    }

    public List<OwnerResponseDto> getOwnersByFirstName(String firstName) {
        log.info("OwnerService::getOwnersByFirstName started");

        List<Owner> owners = ownerRepository.findByFirstNameContainingIgnoreCase(firstName);
        log.info("Found {} owners with first name '{}'", owners.size(), firstName);

        if (owners.isEmpty()) {
            throw new RuntimeException("No owners found with first name: " + firstName);
        }

        log.info("OwnerService::getOwnersByFirstName finished");
        return ownerMapper.mapToOwnerResponseDtoList(owners);
    }

    public List<OwnerResponseDto> getOwnersByLastName(String lastName) {
        log.info("OwnerService::getOwnersByLastName started");

        List<Owner> owners = ownerRepository.findByLastNameContainingIgnoreCase(lastName);
        log.info("Found {} owners with last name '{}'", owners.size(), lastName);

        if (owners.isEmpty()) {
            throw new RuntimeException("No owners found with last name: " + lastName);
        }

        log.info("OwnerService::getOwnersByLastName finished");
        return ownerMapper.mapToOwnerResponseDtoList(owners);
    }

    public Optional<OwnerResponseDto> getOwnerByPhoneNumber(String phoneNumber) {
        log.info("OwnerService::getOwnerByPhoneNumber started");

        Optional<Owner> owner = ownerRepository.findByPhoneNumber(phoneNumber);
        log.info("Found owner with phone number '{}'", phoneNumber);

        if (owner.isEmpty()) {
            throw new RuntimeException("No owner found with phone number: " + phoneNumber);
        }

        log.info("OwnerService::getOwnerByPhoneNumber finished");
        return owner.map(ownerMapper::mapToOwnerResponseDto);
    }

    public Optional<OwnerResponseDto> getOwnerByEmail(String email) {
        log.info("OwnerService::getOwnerByEmail started");

        Optional<Owner> owner = ownerRepository.findByEmail(email);
        log.info("Found owner with email '{}'", email);

        if (owner.isEmpty()) {
            throw new RuntimeException("No owner found with email: " + email);
        }

        log.info("OwnerService::getOwnerByEmail finished");
        return owner.map(ownerMapper::mapToOwnerResponseDto);
    }

    public List<OwnerResponseDto> findOwnersByOwnerDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("OwnerService::findOwnersByOwnerDateBetween started");

        List<Owner> owners = ownerRepository.findByOwnerDateBetween(startDate, endDate);
        log.info("Found {} owners between dates '{}' and '{}'", owners.size(), startDate, endDate);

        if (owners.isEmpty()) {
            throw new RuntimeException("No owners found between dates: " + startDate + " and " + endDate);
        }

        log.info("OwnerService::findOwnersByOwnerDateBetween finished");
        return ownerMapper.mapToOwnerResponseDtoList(owners);
    }

    public List<OwnerResponseDto> findOwnersByUpdatedAtAfter(LocalDateTime date) {
        log.info("OwnerService::findOwnersByUpdatedAtAfter started");

        List<Owner> owners = ownerRepository.findByUpdatedAtAfter(date);
        log.info("Found {} owners updated after date '{}'", owners.size(), date);

        if (owners.isEmpty()) {
            throw new RuntimeException("No owners found updated after date: " + date);
        }

        log.info("OwnerService::findOwnersByUpdatedAtAfter finished");
        return ownerMapper.mapToOwnerResponseDtoList(owners);
    }

    // Sahibi güncelle
    @Transactional
    public OwnerResponseDto updateOwner(String id, OwnerRequestDto ownerRequestDto) {
        log.info("OwnerService::updateOwner started for id: {}", id);

        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));
        log.info("OwnerService::updateOwner Owner found: {}", existingOwner);

        // Güncellemeleri uygulayın
        existingOwner.setFirstName(ownerRequestDto.getFirstName());
        existingOwner.setLastName(ownerRequestDto.getLastName());
        existingOwner.setPhoneNumber(ownerRequestDto.getPhoneNumber());
        existingOwner.setEmail(ownerRequestDto.getEmail());
        existingOwner.setAddress(ownerRequestDto.getAddress());
        existingOwner.setUpdatedAt(LocalDateTime.now());

        Owner updatedOwner = ownerRepository.save(existingOwner);
        log.info("OwnerService::updateOwner Owner updated: {}", updatedOwner);

        log.info("OwnerService::updateOwner finished");
        return ownerMapper.mapToOwnerResponseDto(updatedOwner);
    }


    // Sahibi sil
    @Transactional
    public void deleteOwner(String id) {
        log.info("OwnerService::deleteOwner started for id: {}", id);
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));

        ownerRepository.delete(existingOwner);
        log.info("OwnerService::deleteOwner finished");
    }

}
