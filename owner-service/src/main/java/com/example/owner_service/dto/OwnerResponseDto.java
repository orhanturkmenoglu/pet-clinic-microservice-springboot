package com.example.owner_service.dto;

import com.example.owner_service.model.Pet;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerResponseDto implements Serializable {

    String id;
    String firstName;
    String lastName;
    private String phoneNumber;
    String email;
    String address;

    @Transient
    List<Pet> pets;
}