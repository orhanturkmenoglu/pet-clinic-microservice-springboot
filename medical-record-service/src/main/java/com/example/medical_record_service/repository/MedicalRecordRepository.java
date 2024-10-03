package com.example.medical_record_service.repository;

import com.example.medical_record_service.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord,String> {

    // Pet ID'ye göre tıbbi kayıtları bul
    List<MedicalRecord> findByPetId(String petId);

    // Veteriner ID'ye göre tıbbi kayıtları bul
    List<MedicalRecord> findByVetId(String vetId);

    // Belirli bir tarih aralığında tıbbi kayıtları bul
    List<MedicalRecord> findByVisitDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Güncellenme tarihine göre tıbbi kayıtları bul
    List<MedicalRecord> findByUpdatedAtAfter(LocalDateTime date);
}
