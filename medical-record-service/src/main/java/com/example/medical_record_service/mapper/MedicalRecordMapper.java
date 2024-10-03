package com.example.medical_record_service.mapper;

import com.example.medical_record_service.dto.MedicalRecordRequestDto;
import com.example.medical_record_service.dto.MedicalRecordResponseDto;
import com.example.medical_record_service.model.MedicalRecord;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicalRecordMapper {

    public MedicalRecordResponseDto mapToMedicalRecordResponseDto(MedicalRecord medicalRecord) {
        return MedicalRecordResponseDto.builder()
                .id(medicalRecord.getId())
                .petId(medicalRecord.getPetId())
                .vetId(medicalRecord.getVetId())
                .visitDate(LocalDateTime.now())
                .updatedAt(medicalRecord.getUpdatedAt())
                .diagnosis(medicalRecord.getDiagnosis())
                .treatment(medicalRecord.getTreatment())
                .build();
    }

    public MedicalRecord mapToMedicalRecord(MedicalRecordRequestDto medicalRecordRequestDto) {
        return MedicalRecord.builder()
                .petId(medicalRecordRequestDto.getPetId())
                .vetId(medicalRecordRequestDto.getVetId())
                .visitDate(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .diagnosis(medicalRecordRequestDto.getDiagnosis())
                .treatment(medicalRecordRequestDto.getTreatment())
                .build();
    }

    public List<MedicalRecordResponseDto> mapToMedicalRecordResponseDtoList(List<MedicalRecord> medicalRecords) {
        return medicalRecords.stream()
                .map(this::mapToMedicalRecordResponseDto)
                .collect(Collectors.toList());
    }
}
