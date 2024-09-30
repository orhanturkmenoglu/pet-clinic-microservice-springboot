package com.example.pet.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
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

    private Double weight;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime petDate;


}

