package com.example.vet_service.dto;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeterinarianResponseDto implements Serializable {

    private String firstName;
    private String lastName;
    private String specialization;
    private String availability; 
    private String phoneNumber;
    private String email;
    private String address;

}