package com.example.owner_service.service;

import com.example.owner_service.dto.OwnerRequestDto;
import com.example.owner_service.dto.OwnerResponseDto;
import com.example.owner_service.external.PetClientService;
import com.example.owner_service.mapper.OwnerMapper;
import com.example.owner_service.model.Owner;
import com.example.owner_service.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerService {

    private final OwnerRepository ownerRepository;

    private final RestTemplate restTemplate;

    private final PetClientService petClientService;

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



    // Sahibi bulma
    public OwnerResponseDto findOwnerById(String id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        // feign client ile http tabanlı servisler ile iletişim kurabiliriz rest template ile aynı işlevi yerine
        // getirir ama feign client daha kolay ve okunabilir bir şekilde yazılır. kod yazımıını azaltır

        OwnerResponseDto ownerResponseDto = ownerMapper.mapToOwnerResponseDto(owner);

        log.info("OwnerResponseDto::findOwnerById Owner: {}", ownerResponseDto);
        return ownerResponseDto;
    }

    // Sahibi güncelleme
    public OwnerResponseDto updateOwner(String id, OwnerRequestDto ownerRequest) {
        Optional<Owner> ownerOpt = ownerRepository.findById(id);
        if (ownerOpt.isPresent()) {
            Owner owner = ownerOpt.get();
            owner.setFirstName(ownerRequest.getFirstName());
            owner.setLastName(ownerRequest.getLastName());
            owner.setPhoneNumber(ownerRequest.getPhoneNumber());
            owner.setEmail(ownerRequest.getEmail());
            owner.setAddress(ownerRequest.getAddress());

            Owner updatedOwner = ownerRepository.save(owner);
            return ownerMapper.mapToOwnerResponseDto(updatedOwner);
        }
        return null; // Veya özel bir hata fırlatabilirsiniz
    }

    // Sahibi silme
    public void deleteOwner(String id) {
        ownerRepository.deleteById(id);
    }

}
