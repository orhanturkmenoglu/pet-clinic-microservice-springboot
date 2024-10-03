package com.example.vet_service.repository;

import com.example.vet_service.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, String> {

    List<Veterinarian> findBySpecializationIsLikeIgnoreCase(String  firstName);

    List<Veterinarian> findByAvailability(String availability);

    // İsim veya soyadı ile veterinerleri bul
    List<Veterinarian> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    // Telefon numarasına göre veterinerleri bul
    List<Veterinarian> findByPhoneNumber(String phoneNumber);


    // Belirli bir uzmanlık alanına sahip ve müsait olan veterinerleri bul
    List<Veterinarian> findBySpecializationAndAvailability(String specialization, String availability);


    // Veterinerlerin adresine göre arama yap
    List<Veterinarian> findByAddressContaining(String address);

    // Belirli bir tarih aralığında kayıtlı olan veterinerleri bul
    List<Veterinarian> findByVeterinarianDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}