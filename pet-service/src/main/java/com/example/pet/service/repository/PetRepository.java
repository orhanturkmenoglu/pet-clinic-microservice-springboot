package com.example.pet.service.repository;

import com.example.pet.service.model.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository  extends MongoRepository<Pet, String> {
}
