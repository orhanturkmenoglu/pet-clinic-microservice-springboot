package com.example.medical_record_service.service;

import com.example.medical_record_service.dto.MedicalRecordRequestDto;
import com.example.medical_record_service.dto.MedicalRecordResponseDto;
import com.example.medical_record_service.model.MedicalRecord;
import com.example.medical_record_service.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;


    @Transactional
    public MedicalRecordResponseDto createMedicalRecord(MedicalRecordRequestDto medicalRecordRequestDto) {
        log.info("MedicalRecordService::createMedicalRecord started");

        MedicalRecord medicalRecord = mapToMedicalRecord(medicalRecordRequestDto);

        MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);

        log.info("MedicalRecordService::createMedicalRecord finished");
        return mapToMedicalRecordResponseDto(savedMedicalRecord);
    }

    private MedicalRecordResponseDto mapToMedicalRecordResponseDto(MedicalRecord medicalRecord) {
        return MedicalRecordResponseDto.builder()
                .id(medicalRecord.getId())
                .petId(medicalRecord.getPetId())
                .vetId(medicalRecord.getVetId())
                .visitDate(LocalDateTime.now())
                .diagnosis(medicalRecord.getDiagnosis())
                .treatment(medicalRecord.getTreatment())
                .build();
    }

    private MedicalRecord mapToMedicalRecord(MedicalRecordRequestDto medicalRecordRequestDto) {
        return MedicalRecord.builder()
                .petId(medicalRecordRequestDto.getPetId())
                .vetId(medicalRecordRequestDto.getVetId())
                .visitDate(LocalDateTime.now())
                .diagnosis(medicalRecordRequestDto.getDiagnosis())
                .treatment(medicalRecordRequestDto.getTreatment())
                .build();
    }
}
