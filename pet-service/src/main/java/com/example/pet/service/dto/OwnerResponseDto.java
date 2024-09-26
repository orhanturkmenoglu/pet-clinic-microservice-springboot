package com.example.pet.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
}