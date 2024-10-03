package com.example.pet.service.repository;

import com.example.pet.service.model.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository  extends MongoRepository<Pet, String> {

    // Belirli bir sahibin evcil hayvanlarını bul
    Optional<Pet> findPetByOwnerId(String ownerId);

    // İsimle evcil hayvanları bul
    List<Pet> findByNameContainingIgnoreCase(String name);

    // Cinsle evcil hayvanları bul
    List<Pet> findByBreed(String breed);

    // Yaşla evcil hayvanları bul
    List<Pet> findByAge(String age);

    // Renkle evcil hayvanları bul
    List<Pet> findByColor(String color);

    // Belirli bir tarih aralığında kaydedilen evcil hayvanları bul
    List<Pet> findByPetDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Güncellenme tarihine göre evcil hayvanları bul
    List<Pet> findByUpdatedAtAfter(LocalDateTime date);

    // Cinsiyete göre evcil hayvan bul
    List<Pet> findByGender(String gender);
}
