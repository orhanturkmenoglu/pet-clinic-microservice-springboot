package com.example.owner_service.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerRequestDto implements Serializable {

    String id;
    String firstName;
    String lastName;
    private String phoneNumber;
    String email;
    String address;

}