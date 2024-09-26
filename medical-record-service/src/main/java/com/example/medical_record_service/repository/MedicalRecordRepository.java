package com.example.medical_record_service.repository;

import com.example.medical_record_service.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord,String> {
}
