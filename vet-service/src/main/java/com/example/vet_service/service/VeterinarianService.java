package com.example.vet_service.service;

import com.example.vet_service.dto.VeterinarianRequestDto;
import com.example.vet_service.dto.VeterinarianResponseDto;
import com.example.vet_service.mapper.VeterinarianMapper;
import com.example.vet_service.model.Veterinarian;
import com.example.vet_service.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;

    private final VeterinarianMapper veterinarianMapper;

    @Transactional
    public VeterinarianResponseDto createVeterinarian(VeterinarianRequestDto veterinarianRequestDto) {
        log.info("VeterinarianService::createVeterinarian started");

        Veterinarian veterinarian = veterinarianMapper.mapToVeterinarian(veterinarianRequestDto);
        Veterinarian savedVeterinarian = veterinarianRepository.save(veterinarian);


        log.info("VeterinarianService::createVeterinarian  veterinarian :{} ," +
                "savedVeterinarian : {}", veterinarian, savedVeterinarian);

        log.info("VeterinarianService::createVeterinarian finished");
        return veterinarianMapper.mapToVeterinarianResponseDto(savedVeterinarian);
    }

    public List<VeterinarianResponseDto> getAllVeterinarians() {
        log.info("VeterinarianService::getAllVeterinarians started");

        List<Veterinarian> veterinarians = veterinarianRepository.findAll();

        log.info("VeterinarianService::getAllVeterinarians finished");
        return veterinarianMapper.mapToVeterinarianResponseDtoList(veterinarians);
    }

    public VeterinarianResponseDto getVeterinarianById(String id) {
        log.info("VeterinarianService::getVeterinarianById started");

        Veterinarian veterinarian = veterinarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found with id: " + id));

        return veterinarianMapper.mapToVeterinarianResponseDto(veterinarian);
    }

    public List<VeterinarianResponseDto> getVeterinarianBySpecialization(String  firstName){
        log.info("VeterinarianService::getVeterinarianBySpecialization started");

        List<Veterinarian> veterinarians = veterinarianRepository
                .findBySpecializationIsLikeIgnoreCase(firstName);

        log.info("VeterinarianService::getVeterinarianBySpecialization  veterinarians :{} ,"
                ,veterinarians);

        return veterinarianMapper.mapToVeterinarianResponseDtoList(veterinarians);
    }


    // Müsaitlik durumuna göre veterinerleri getir
    public List<VeterinarianResponseDto> getVeterinariansByAvailability(String availability) {
        log.info("VeterinarianService::getVeterinariansByAvailability started");

        List<Veterinarian> veterinarians = veterinarianRepository.findByAvailability(availability);

        log.info("VeterinarianService::getVeterinariansByAvailability  veterinarians :{} ,"
                ,veterinarians);


        log.info("VeterinarianService::getVeterinariansByAvailability finished");
        return veterinarianMapper.mapToVeterinarianResponseDtoList(veterinarians);
    }

    // İsim veya soyadı ile veterinerleri bul
    public List<VeterinarianResponseDto> getVeterinariansByName(String firstName, String lastName) {
        log.info("VeterinarianService::getVeterinariansByName started");

        // İsim veya soyadı ile veterinerleri bul
        List<Veterinarian> veterinarians = veterinarianRepository.
                findByFirstNameContainingOrLastNameContaining(firstName, lastName);
        log.info("VeterinarianService::getVeterinariansByName  veterinarians :{} ,"
                ,veterinarians);

        log.info("VeterinarianService::getVeterinariansByName finished");
        return veterinarianMapper.mapToVeterinarianResponseDtoList(veterinarians);
    }

    // Telefon numarasına göre veterinerleri bul
    public List<VeterinarianResponseDto> getVeterinariansByPhoneNumber(String phoneNumber) {
        log.info("VeterinarianService::getVeterinariansByPhoneNumber started");

        List<Veterinarian> veterinarians = veterinarianRepository.findByPhoneNumber(phoneNumber);
        log.info("VeterinarianService::getVeterinariansByPhoneNumber  veterinarians :{} ,"
                ,veterinarians);

        log.info("VeterinarianService::getVeterinariansByPhoneNumber finished");
        return veterinarianMapper.mapToVeterinarianResponseDtoList(veterinarians);
    }

    // Belirli bir uzmanlık alanına sahip ve müsait olan veterinerleri bul
    public List<VeterinarianResponseDto> getVeterinariansBySpecializationAndAvailability(String specialization,
                                                                                         String availability) {
        log.info("VeterinarianService::getVeterinariansBySpecializationAndAvailability started");

        List<Veterinarian> veterinarians = veterinarianRepository.
                findBySpecializationAndAvailability(specialization, availability);

        log.info("VeterinarianService::getVeterinariansBySpecializationAndAvailability  veterinarians :{} ,"
                ,veterinarians);

        log.info("VeterinarianService::getVeterinariansBySpecializationAndAvailability finished");
        return veterinarianMapper.mapToVeterinarianResponseDtoList(veterinarians);
    }

    // Veterinerlerin adresine göre arama yap
    public List<VeterinarianResponseDto> getVeterinariansByAddress(String address) {
        log.info("VeterinarianService::getVeterinariansByAddress started");

        // Adres bilgisine göre veterinerleri bul
        List<Veterinarian> veterinarians = veterinarianRepository.findByAddressContaining(address);
        log.info("VeterinarianService::getVeterinariansByAddress  veterinarians :{} ,"
                ,veterinarians);

        log.info("VeterinarianService::getVeterinariansByAddress finished");
        return veterinarianMapper.mapToVeterinarianResponseDtoList(veterinarians);
    }

    // Belirli bir tarih aralığında kayıtlı olan veterinerleri bul
    public List<VeterinarianResponseDto> getVeterinariansByDateRange(LocalDateTime startDate,
                                                                     LocalDateTime endDate) {
        log.info("VeterinarianService::getVeterinariansByDateRange started");

        // Belirli bir tarih aralığında kayıtlı olan veterinerleri bul
        List<Veterinarian> veterinarians = veterinarianRepository.
                findByVeterinarianDateBetween(startDate, endDate);
        log.info("VeterinarianService::getVeterinariansByDateRange  veterinarians :{} ,"
                ,veterinarians);

        log.info("VeterinarianService::getVeterinariansByDateRange finished");
        return veterinarianMapper.mapToVeterinarianResponseDtoList(veterinarians);
    }

    // Raporu güncelle
    @Transactional
    public VeterinarianResponseDto updateVeterinarian(String id,
                                                      VeterinarianRequestDto veterinarianRequestDto) {
        log.info("VeterinarianService::updateVeterinarian started for id: {}", id);

        Veterinarian veterinarian = veterinarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found with id: " + id));

        // DTO'dan alınan bilgilerle veterinerin alanlarını güncelle
        veterinarian.setFirstName(veterinarianRequestDto.getFirstName());
        veterinarian.setLastName(veterinarianRequestDto.getLastName());
        veterinarian.setSpecialization(veterinarianRequestDto.getSpecialization());
        veterinarian.setAvailability(veterinarianRequestDto.getAvailability());
        veterinarian.setPhoneNumber(veterinarianRequestDto.getPhoneNumber());
        veterinarian.setEmail(veterinarianRequestDto.getEmail());
        veterinarian.setAddress(veterinarianRequestDto.getAddress());
        veterinarian.setUpdatedAt(LocalDateTime.now()); // Güncellenme tarihini ayarla

        Veterinarian updatedVeterinarian = veterinarianRepository.save(veterinarian);
        log.info("VeterinarianService::updateVeterinarian finished, updatedVeterinarian: {}", updatedVeterinarian);

        return veterinarianMapper.mapToVeterinarianResponseDto(updatedVeterinarian);
    }

    public void deleteVeterinarian(String id) {
        log.info("VeterinarianService::deleteVeterinarian started");

        Veterinarian veterinarian = veterinarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found with id: " + id));

        log.info("VeterinarianService::deleteVeterinarian  veterinarian :{} ,", veterinarian);

        veterinarianRepository.delete(veterinarian);

        log.info("VeterinarianService::deleteVeterinarian finished");
    }


}
