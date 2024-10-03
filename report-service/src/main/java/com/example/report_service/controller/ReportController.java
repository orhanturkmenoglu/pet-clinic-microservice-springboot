package com.example.report_service.controller;

import com.example.report_service.dto.ReportRequestDto;
import com.example.report_service.dto.ReportResponseDto;
import com.example.report_service.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private ReportService reportService;


    @Operation(summary = "Create a new report")
    @PostMapping
    public ResponseEntity<ReportResponseDto> createReport(@RequestBody ReportRequestDto reportRequestDto) {
        ReportResponseDto createdReport = reportService.createReport(reportRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReport);
    }

    @Operation(summary = "Get all reports")
    @GetMapping
    public ResponseEntity<List<ReportResponseDto>> getAllReports() {
        List<ReportResponseDto> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Get reports by pet ID")
    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<ReportResponseDto>> getReportsByPetId(
            @Parameter(description = "ID of the pet")
            @PathVariable String petId) {
        List<ReportResponseDto> reports = reportService.getReportsByPetId(petId);
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Get reports by vet ID")
    @GetMapping("/vet/{vetId}")
    public ResponseEntity<List<ReportResponseDto>> getReportsByVetId(
            @Parameter(description = "ID of the vet")
            @PathVariable String vetId) {
        List<ReportResponseDto> reports = reportService.getReportsByVetId(vetId);
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Get report by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDto> getReportById(
            @Parameter(description = "ID of the report")
            @PathVariable String id) {
        ReportResponseDto report = reportService.getReportById(id).orElse(null);
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Get reports by content keyword")
    @GetMapping("/search/content")
    public ResponseEntity<List<ReportResponseDto>> getReportsByContent(
            @Parameter(description = "Keyword to search in report content")
            @RequestParam String keyword) {
        List<ReportResponseDto> reports = reportService.getReportsByContent(keyword);
        return ResponseEntity.ok(reports);
    }


    @Operation(summary = "Get reports by status")
    @GetMapping("/search/status")
    public ResponseEntity<List<ReportResponseDto>> getReportsByStatus(
            @Parameter(description = "Status of the report")
            @RequestParam String status) {
        List<ReportResponseDto> reports = reportService.getReportsByStatus(status);
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Get reports by pet ID and status")
    @GetMapping("/search/pet/{petId}/status")
    public ResponseEntity<List<ReportResponseDto>> getReportsByPetIdAndStatus(
            @Parameter(description = "ID of the pet")
            @PathVariable String petId,
            @Parameter(description = "Status of the report")
            @RequestParam String status) {
        List<ReportResponseDto> reports = reportService.getReportsByPetIdAndStatus(petId, status);
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Get reports by report date")
    @GetMapping("/search/date")
    public ResponseEntity<List<ReportResponseDto>> getReportsByReportDate(
            @Parameter(description = "Report date in the format yyyy-MM-dd'T'HH:mm:ss")
            @RequestParam LocalDateTime reportDate) {
        List<ReportResponseDto> reports = reportService.getReportsByReportDate(reportDate);
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Get reports within date range")
    @GetMapping("/search/date/range")
    public ResponseEntity<List<ReportResponseDto>> getReportsWithinDateRange(
            @Parameter(description = "Start date in the format yyyy-MM-dd'T'HH:mm:ss")
            @RequestParam LocalDateTime startDate,
            @Parameter(description = "End date in the format yyyy-MM-dd'T'HH:mm:ss")
            @RequestParam LocalDateTime endDate) {
        List<ReportResponseDto> reports = reportService.getReportsWithinDateRange(startDate, endDate);
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Update a report")
    @PutMapping("/{id}")
    public ResponseEntity<ReportResponseDto> updateReport(
            @Parameter(description = "ID of the report")
            @PathVariable String id,
             @RequestBody ReportRequestDto reportRequestDto) {
        ReportResponseDto updatedReport = reportService.updateReport(id, reportRequestDto);
        return ResponseEntity.ok(updatedReport);
    }

    @Operation(summary = "Delete a report")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(
            @Parameter(description = "ID of the report") @PathVariable String id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
