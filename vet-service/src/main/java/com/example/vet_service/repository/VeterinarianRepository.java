package com.example.vet_service.repository;

import com.example.vet_service.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, String> {

    List<Veterinarian> findBySpecializationIsLikeIgnoreCase(String  firstName);
}