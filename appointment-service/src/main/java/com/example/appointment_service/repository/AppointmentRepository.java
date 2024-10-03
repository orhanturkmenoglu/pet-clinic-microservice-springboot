package com.example.appointment_service.repository;

import com.example.appointment_service.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    // Belirli bir veteriner için tüm randevuları al
    List<Appointment> findByVetId(String vetId);

    // Belirli bir pet için tüm randevuları al
    List<Appointment> findByPetId(String petId);

    // Belirli bir tarih aralığında randevuları al
    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate BETWEEN :startDate AND :endDate")
    List<Appointment> findByAppointmentDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Randevu durumuna göre randevuları al
    List<Appointment> findByStatus(String status);

    // Randevu tarihine göre duruma göre randevuları al
    @Query("SELECT a FROM Appointment a WHERE a.status = :status AND a.appointmentDate = :appointmentDate")
    List<Appointment> findByStatusAndAppointmentDate(@Param("status") String status, @Param("appointmentDate") LocalDateTime appointmentDate);

    // Son güncellenme tarihinden sonra güncellenmiş randevuları al
    @Query("SELECT a FROM Appointment a WHERE a.updatedAt > :updatedAt")
    List<Appointment> findByUpdatedAtAfter(@Param("updatedAt") LocalDateTime updatedAt);
}