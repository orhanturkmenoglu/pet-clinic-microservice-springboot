package com.example.report_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportRequestDto implements Serializable {

    private String id;
    private String petId;
    private LocalDate createdDate;
    private String content;

}
