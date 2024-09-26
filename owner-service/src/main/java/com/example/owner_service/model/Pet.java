package com.example.owner_service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    private String id;

    private String ownerId;  // sahip kimliği.

    private String name;

    private String type; // türü kedi,köpek vb

    private String breed;  // cinsi

    private String age;

    private String gender;

    private String color;

    private String description;

}
