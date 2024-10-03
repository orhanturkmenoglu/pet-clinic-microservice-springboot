package com.example.report_service.service;

import com.example.report_service.dto.ReportRequestDto;
import com.example.report_service.dto.ReportResponseDto;
import com.example.report_service.mapper.ReportMapper;
import com.example.report_service.model.Report;
import com.example.report_service.repository.ReportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;

    // Yeni bir rapor oluştur
    @Transactional
    public ReportResponseDto createReport(ReportRequestDto reportRequestDto) {
        log.info("ReportService::createReport started");

        Report report = reportMapper.mapToReport(reportRequestDto);
        Report savedReport = reportRepository.save(report);

        log.info("ReportService::createReport report: {} savedReport: {}", report,
                savedReport);


        log.info("ReportService::createReport finished");
        return reportMapper.mapToReportResponseDto(savedReport);
    }

    // Raporları getir
    public List<ReportResponseDto> getAllReports() {
        log.info("ReportService::getAllReports started");

        List<Report> reportList = reportRepository.findAll();
        log.info("ReportService::createReport reportList: {}", reportList);

        log.info("ReportService::getAllReports finished");
        return reportMapper.mapToReportResponseDtoList(reportList);
    }

    // Pet ID'sine göre raporları getir
    public List<ReportResponseDto> getReportsByPetId(String petId) {
        log.info("ReportService::getReportsByPetId started");
        // Pet ID'sine göre tüm raporları al
        List<Report> petList = reportRepository.findByPetId(petId);
        log.info("ReportService::getReportsByPetId petList: {}", petList);

        // Eğer rapor bulunamazsa hata fırlat
        if (petList.isEmpty()) {
            throw new RuntimeException("No reports found with petId: " + petId);
        }

        log.info("ReportService::getReportsByPetId finished");
        return reportMapper.mapToReportResponseDtoList(petList);
    }


    // Vet ID'sine göre raporları getir
    public List<ReportResponseDto> getReportsByVetId(String vetId) {
        log.info("ReportService::getReportsByVetId started");

        List<Report> vetList = reportRepository.findByVetId(vetId);
        log.info("ReportService::getReportsByVetId vetList: {}", vetList);

        if (vetList.isEmpty()) {
            throw new RuntimeException("No reports found with vetId: " + vetId);
        }

        log.info("ReportService::getReportsByVetId finished");
        return reportMapper.mapToReportResponseDtoList(vetList);
    }

    // Rapor ID'sine göre rapor getir
    public Optional<ReportResponseDto> getReportById(String id) {
        log.info("ReportService::getReportById started");
        // Rapor ID'sine göre rapor getir
        Report report = reportRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Report not found with id: " + id));
        log.info("ReportService::getReportById report: {}", report);

        log.info("ReportService::getReportById finished");
        return Optional.ofNullable(reportMapper.mapToReportResponseDto(report));
    }

    // Rapor içeriklerine göre raporları getir
    public List<ReportResponseDto> getReportsByContent(String keyword) {
        log.info("ReportService::getReportsByContent started");

        List<Report> reportsByContent = reportRepository.findByContentContaining(keyword);
        log.info("ReportService::getReportsByContent reportsByContent: {}", reportsByContent);

        if (reportsByContent.isEmpty()) {
            throw new RuntimeException("No reports found with content containing: " + keyword);
        }

        log.info("ReportService::getReportsByContent finished");
        return reportMapper.mapToReportResponseDtoList(reportsByContent);
    }

    // Rapor durumuna göre raporları getir
    public List<ReportResponseDto> getReportsByStatus(String status) {
        log.info("ReportService::getReportsByStatus started");

        List<Report> reportsByStatus = reportRepository.findByStatus(status);
        log.info("ReportService::getReportsByStatus reportsByStatus: {}", reportsByStatus);

        if (reportsByStatus.isEmpty()) {
            throw new RuntimeException("No reports found with status: " + status);
        }

        log.info("ReportService::getReportsByStatus finished");
        return reportMapper.mapToReportResponseDtoList(reportsByStatus);
    }

    // Pet ID'si ve rapor durumuna göre raporları getir
    public List<ReportResponseDto> getReportsByPetIdAndStatus(String petId, String status) {
        log.info("ReportService::getReportsByPetIdAndStatus started");

        List<Report> reportsByPetIdAndStatus = reportRepository.findByPetIdAndStatus(petId, status);
        log.info("ReportService::getReportsByPetIdAndStatus reportsByPetIdAndStatus: {}", reportsByPetIdAndStatus);

        if (reportsByPetIdAndStatus.isEmpty()) {
            throw new RuntimeException("No reports found with petId: " + petId + " and status: " + status);
        }

        log.info("ReportService::getReportsByPetIdAndStatus finished");
        return reportMapper.mapToReportResponseDtoList(reportsByPetIdAndStatus);
    }

    // Rapor tarihine göre raporları getir
    public List<ReportResponseDto> getReportsByReportDate(LocalDateTime reportDate) {
        log.info("ReportService::getReportsByReportDate started");

        List<Report> reportsByReportDate = reportRepository.findByReportDate(reportDate);
        log.info("ReportService::getReportsByReportDate reportsByReportDate: {}", reportsByReportDate);

        if (reportsByReportDate.isEmpty()) {
            throw new RuntimeException("No reports found with reportDate: " + reportDate);
        }

        log.info("ReportService::getReportsByReportDate finished");
        return reportMapper.mapToReportResponseDtoList(reportsByReportDate);
    }

    // Belirli bir tarih aralığında raporları getir
    public List<ReportResponseDto> getReportsWithinDateRange(LocalDateTime startDate,
                                                             LocalDateTime endDate) {
        log.info("ReportService::getReportsWithinDateRange started");

        List<Report> reportsWithinDateRange = reportRepository.findReportsWithinDateRange(startDate, endDate);
        log.info("ReportService::getReportsWithinDateRange reportsWithinDateRange: {}", reportsWithinDateRange);

        if (reportsWithinDateRange.isEmpty()) {
            throw new RuntimeException("No reports found within the specified date range.");
        }

        log.info("ReportService::getReportsWithinDateRange finished");
        return reportMapper.mapToReportResponseDtoList(reportsWithinDateRange);
    }

    // Raporu güncelle
    @Transactional
    public ReportResponseDto updateReport(String id, ReportRequestDto reportRequestDto) {
        log.info("ReportService::updateReport started");
        // Rapor ID'sine göre rapor getir
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
        log.info("ReportService::updateReport report: {}", report);

        Report updateReport = reportMapper.mapToUpdatedReportDto(reportRequestDto, report);
        log.info("ReportService::updateReport updateReport: {}", updateReport);


        Report updatedReport = reportRepository.save(updateReport);
        log.info("ReportService::updateReport updatedReport: {}", updatedReport);

        log.info("ReportService::updateReport finished");
        return reportMapper.mapToReportResponseDto(updatedReport);
    }




    // Raporu sil
    @Transactional
    public void deleteReport(String id) {
        log.info("ReportService::deleteReport started");
        // Rapor ID'sine göre rapor getir
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
        log.info("ReportService::deleteReport report: {}", report);

        // Raporu sil
        log.info("ReportService::deleteReport finished");
        reportRepository.deleteById(id);
    }


}
