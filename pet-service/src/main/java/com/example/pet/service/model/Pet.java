package com.example.pet.service.model;

import com.example.pet.service.dto.OwnerResponseDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "pets")
public class Pet implements Serializable {
    // hayvan bilgilerine sahip sınıf.

    @Id
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

