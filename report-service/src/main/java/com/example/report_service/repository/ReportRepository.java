package com.example.report_service.repository;

import com.example.report_service.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, String> {

    // Pet ID'sine göre raporları getir
    List<Report> findByPetId(String petId);

    // Vet ID'sine göre raporları getir
    List<Report> findByVetId(String vetId);

    // Rapor tarihine göre raporları getir
    List<Report> findByReportDate(LocalDateTime reportDate);

    // Rapor durumuna göre raporları getir
    List<Report> findByStatus(String status);

    // Pet ID'si ve rapor durumuna göre raporları getir
    List<Report> findByPetIdAndStatus(String petId, String status);

    // Rapor içeriklerine göre raporları getir
    List<Report> findByContentContaining(String keyword);

    // Belirli bir tarih aralığında raporları getir
    @Query("SELECT r FROM Report r WHERE r.reportDate BETWEEN :startDate AND :endDate")
    List<Report> findReportsWithinDateRange(@Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);
}