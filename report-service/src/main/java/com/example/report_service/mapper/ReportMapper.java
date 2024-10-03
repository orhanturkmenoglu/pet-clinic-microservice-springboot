package com.example.report_service.mapper;

import com.example.report_service.dto.ReportRequestDto;
import com.example.report_service.dto.ReportResponseDto;
import com.example.report_service.model.Report;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReportMapper {

    public Report mapToReport(ReportRequestDto reportRequestDto) {
        return Report.builder()
                .petId(reportRequestDto.getPetId())
                .vetId(reportRequestDto.getVetId())
                .billingId(reportRequestDto.getBillingId())
                .appointmentId(reportRequestDto.getAppointmentId())
                .medicalRecordId(reportRequestDto.getMedicalRecordId())
                .status(reportRequestDto.getStatus())
                .content(reportRequestDto.getContent())
                .reportType(reportRequestDto.getReportType())
                .reportDate(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }


    public ReportResponseDto mapToReportResponseDto(Report report) {
        return ReportResponseDto.builder()
                .id(report.getId())
                .petId(report.getPetId())
                .vetId(report.getVetId())
                .billingId(report.getBillingId())
                .appointmentId(report.getAppointmentId())
                .medicalRecordId(report.getMedicalRecordId())
                .status(report.getStatus())
                .content(report.getContent())
                .reportType(report.getReportType())
                .reportDate(report.getReportDate())
                .updatedAt(report.getUpdatedAt())
                .build();
    }

    public Report mapToUpdatedReportDto(ReportRequestDto reportRequestDto, Report report) {
        return Report.builder()
                .petId(reportRequestDto.getPetId())
                .vetId(reportRequestDto.getVetId())
                . billingId(reportRequestDto.getBillingId())
                .appointmentId(reportRequestDto.getAppointmentId())
                . medicalRecordId(reportRequestDto.getMedicalRecordId())
                . status(reportRequestDto.getStatus())
                . content(reportRequestDto.getContent())
                . reportType(reportRequestDto.getReportType())
                .reportDate(reportRequestDto.getReportDate())
                .updatedAt(LocalDateTime.now())
                .build();

    }

    public List<ReportResponseDto> mapToReportResponseDtoList(List<Report> reports) {
        return reports.stream()
                .map(this::mapToReportResponseDto)
                .collect(Collectors.toList());
    }

}
