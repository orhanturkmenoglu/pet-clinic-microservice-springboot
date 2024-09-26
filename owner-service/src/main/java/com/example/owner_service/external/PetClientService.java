package com.example.owner_service.external;

import com.example.owner_service.model.Pet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PET-SERVICE")
public interface PetClientService {

    @GetMapping("api/v1/pets/owner/{ownerId}")
     List<Pet> getPetByOwnerId(@PathVariable("ownerId") String ownerId);
}
