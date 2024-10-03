package com.example.medical_record_service.service;

import com.example.medical_record_service.dto.MedicalRecordRequestDto;
import com.example.medical_record_service.dto.MedicalRecordResponseDto;
import com.example.medical_record_service.mapper.MedicalRecordMapper;
import com.example.medical_record_service.model.MedicalRecord;
import com.example.medical_record_service.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    private final MedicalRecordMapper medicalRecordMapper;

    @Transactional
    public MedicalRecordResponseDto createMedicalRecord(MedicalRecordRequestDto medicalRecordRequestDto) {
        log.info("MedicalRecordService::createMedicalRecord started");

        MedicalRecord medicalRecord = medicalRecordMapper.mapToMedicalRecord(medicalRecordRequestDto);
        MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);

        log.info("MedicalRecordService::createMedicalRecord medicalRecor: {} savedMedicalRecord : {}",
                medicalRecord, savedMedicalRecord);

        log.info("MedicalRecordService::createMedicalRecord finished");
        return medicalRecordMapper.mapToMedicalRecordResponseDto(savedMedicalRecord);
    }

    // Tüm tıbbi kayıtları bul
    public List<MedicalRecordResponseDto> getAllMedicalRecords() {
        log.info("MedicalRecordService::getAllMedicalRecords started");

        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        log.info("MedicalRecordService::getAllMedicalRecords medicalRecords: {}", medicalRecords);

        if (medicalRecords.isEmpty()) {
            throw new RuntimeException("MedicalRecordService::getAllMedicalRecords medicalRecords is empty");
        }

        log.info("MedicalRecordService::getAllMedicalRecords finished");
        return medicalRecordMapper.mapToMedicalRecordResponseDtoList(medicalRecords);
    }


    // Tıbbi kaydı ID'ye göre bul
    public MedicalRecordResponseDto getMedicalRecordById(String id) {
        log.info("MedicalRecordService::getMedicalRecordById started");

        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found with id: " + id));
        log.info("MedicalRecordService::getMedicalRecordById medicalRecord: {}", medicalRecord);

        log.info("MedicalRecordService::getMedicalRecordById finished");
        return medicalRecordMapper.mapToMedicalRecordResponseDto(medicalRecord);
    }

    public List<MedicalRecordResponseDto> getByPetId(String petId) {
        log.info("MedicalRecordService::findByPetId started with petId: {}", petId);

        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByPetId(petId);
        log.info("MedicalRecordService::findByPetId medicalRecords: {}", medicalRecords);

        if (petId.isEmpty()) {
            throw new IllegalArgumentException("petId cannot be  empty");
        }

        log.info("MedicalRecordService::findByPetId finished");
        return medicalRecordMapper.mapToMedicalRecordResponseDtoList(medicalRecords);
    }

    public List<MedicalRecordResponseDto> getByVetId(String vetId) {
        log.info("MedicalRecordService::findByVetId started with vetId: {}", vetId);

        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByVetId(vetId);
        log.info("MedicalRecordService::findByVetId medicalRecords: {}", medicalRecords);

        if (vetId.isEmpty()) {
            throw new IllegalArgumentException("vetId cannot be  empty");
        }

        log.info("MedicalRecordService::findByVetId finished");
        return medicalRecordMapper.mapToMedicalRecordResponseDtoList(medicalRecords);
    }

    public List<MedicalRecordResponseDto> findByVisitDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("MedicalRecordService::findByVisitDateBetween started");

        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByVisitDateBetween(startDate, endDate);
        log.info("MedicalRecordService::findByVisitDateBetween medicalRecords: {}", medicalRecords);

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("startDate and endDate cannot be null");
        }

        log.info("MedicalRecordService::findByVisitDateBetween finished");
        return   medicalRecordMapper.mapToMedicalRecordResponseDtoList(medicalRecords);
    }

    public List<MedicalRecordResponseDto> findByUpdatedAtAfter(LocalDateTime date) {
        log.info("MedicalRecordService::findByUpdatedAtAfter started");

        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByUpdatedAtAfter(date);
        log.info("MedicalRecordService::findByUpdatedAtAfter medicalRecords: {}", medicalRecords);

        if (date == null) {
            throw new IllegalArgumentException("date cannot be null");
        }

        log.info("MedicalRecordService::findByUpdatedAtAfter finished");
        return medicalRecordMapper.mapToMedicalRecordResponseDtoList(medicalRecords);
    }


    // Tıbbi kaydı güncelle
    @Transactional
    public MedicalRecordResponseDto updateMedicalRecord(String id, MedicalRecordRequestDto medicalRecordRequestDto) {
        log.info("MedicalRecordService::updateMedicalRecord started");

        // Mevcut tıbbi kaydı bul
        MedicalRecord existingRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found with id: " + id));

        log.info("MedicalRecordService::updateMedicalRecord existingRecord: {}", existingRecord);

        // Güncellemeleri uygulayın
        existingRecord.setPetId(medicalRecordRequestDto.getPetId());
        existingRecord.setVetId(medicalRecordRequestDto.getVetId());
        existingRecord.setVisitDate(LocalDateTime.now());
        existingRecord.setDiagnosis(medicalRecordRequestDto.getDiagnosis());
        existingRecord.setTreatment(medicalRecordRequestDto.getTreatment());
        existingRecord.setUpdatedAt(LocalDateTime.now());

        MedicalRecord updatedRecord = medicalRecordRepository.save(existingRecord);
        log.info("MedicalRecordService::updateMedicalRecord updatedRecord: {}", updatedRecord);

        log.info("MedicalRecordService::updateMedicalRecord finished");
        return medicalRecordMapper.mapToMedicalRecordResponseDto(updatedRecord);
    }

    // Tıbbi kaydı sil
    @Transactional
    public void deleteMedicalRecord(String id) {
        log.info("MedicalRecordService::deleteMedicalRecord started");

        // Mevcut tıbbi kaydı bul
        MedicalRecord existingRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found with id: " + id));

        log.info("MedicalRecordService::deleteMedicalRecord existingRecord: {}", existingRecord);

        log.info("MedicalRecordService::deleteMedicalRecord finished");
        medicalRecordRepository.delete(existingRecord);
    }

}
