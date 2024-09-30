package com.example.report_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object for Report request")
public class ReportRequestDto implements Serializable {

    @Schema(description = "Unique identifier of the report", example = "a1b2c3d4-e5f6-7890-gh12-ijkl3456mnop")
    private String id;

    @Schema(description = "Identifier of the pet associated with the report", example = "pet123")
    private String petId;

    @Schema(description = "The date when the report was created", example = "2023-09-28")
    private LocalDate createdDate;

    @Schema(description = "Content of the report", example = "This report describes the health condition of the pet.")
    private String content;
}
