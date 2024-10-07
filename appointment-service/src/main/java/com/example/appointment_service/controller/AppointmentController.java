package com.example.appointment_service.controller;

import com.example.appointment_service.dto.AppointmentRequestDto;
import com.example.appointment_service.dto.AppointmentResponseDto;
import com.example.appointment_service.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @Operation(summary = "Create a new appointment")
    public ResponseEntity<AppointmentResponseDto> createAppointment(@Valid
            @RequestBody AppointmentRequestDto appointmentRequestDto) {
        AppointmentResponseDto response = appointmentService.createAppointment(appointmentRequestDto);
        return ResponseEntity.status(201).body(response); // 201 Created
    }

    @GetMapping
    @Operation(summary = "Get all appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        List<AppointmentResponseDto> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get appointments by date range")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDateRange(
            @Parameter(description = "Start date of the range")
            @RequestParam LocalDateTime startDate,
            @Parameter(description = "End date of the range")
            @RequestParam LocalDateTime endDate) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByDateRange(startDate, endDate);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get appointments by status")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByStatus(
            @Parameter(description = "Status of the appointments") @PathVariable String status) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByStatus(status);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/status/{status}/date")
    @Operation(summary = "Get appointments by status and date")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByStatusAndDate(
            @Parameter(description = "Status of the appointments") @PathVariable String status,
            @Parameter(description = "Date of the appointment") @RequestParam LocalDateTime appointmentDate) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByStatusAndDate(status, appointmentDate);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/updated-after")
    @Operation(summary = "Get updated appointments after a specific date")
    public ResponseEntity<List<AppointmentResponseDto>> getUpdatedAppointmentsAfter(
            @Parameter(description = "Date to check for updated appointments") @RequestParam LocalDateTime updatedAt) {
        List<AppointmentResponseDto> appointments = appointmentService.getUpdatedAppointmentsAfter(updatedAt);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an appointment by ID")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(
            @Parameter(description = "ID of the appointment") @PathVariable String id) {
        AppointmentResponseDto appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/vet/{vetId}")
    @Operation(summary = "Get all appointments for a specific vet")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByVetId(
            @Parameter(description = "ID of the vet") @PathVariable String vetId) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByVetId(vetId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/pet/{petId}")
    @Operation(summary = "Get all appointments for a specific pet")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByPetId(
            @Parameter(description = "ID of the pet") @PathVariable String petId) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByPetId(petId);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing appointment")
    public ResponseEntity<AppointmentResponseDto> updateAppointment(
            @Parameter(description = "ID of the appointment") @PathVariable String id,
            @RequestBody AppointmentRequestDto appointmentRequestDto) {
        AppointmentResponseDto updatedAppointment = appointmentService.updateAppointment(id, appointmentRequestDto);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an appointment")
    public ResponseEntity<Void> deleteAppointment(
            @Parameter(description = "ID of the appointment") @PathVariable String id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
