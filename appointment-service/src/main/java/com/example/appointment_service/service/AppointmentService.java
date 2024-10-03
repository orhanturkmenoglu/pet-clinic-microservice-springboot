package com.example.appointment_service.service;

import com.example.appointment_service.dto.AppointmentRequestDto;
import com.example.appointment_service.dto.AppointmentResponseDto;
import com.example.appointment_service.mapper.AppointmentMapper;
import com.example.appointment_service.model.Appointment;
import com.example.appointment_service.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final AppointmentMapper appointmentMapper;

    // Randevu oluştur
    @Transactional
    public AppointmentResponseDto createAppointment(AppointmentRequestDto appointmentRequestDto) {
        log.info("AppointmentService::createAppointment started");

        Appointment appointment = appointmentMapper.mapToAppointment(appointmentRequestDto);
        Appointment savedAppointment = appointmentRepository.save(appointment);

        log.info("AppointmentService::createAppointment savedAppointment: {}", savedAppointment);
        return appointmentMapper.mapToAppointmentResponseDto(savedAppointment);
    }

    // Tüm randevuları bul
    public List<AppointmentResponseDto> getAllAppointments() {
        log.info("AppointmentService::getAllAppointments started");

        List<Appointment> appointments = appointmentRepository.findAll();
        if (appointments.isEmpty()) {
            throw new RuntimeException("No appointments found");
        }

        return appointmentMapper.mapToAppointmentResponseDtoList(appointments);
    }

    // Randevu tarihine göre randevuları al
    public List<AppointmentResponseDto> getAppointmentsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("AppointmentService::getAppointmentsByDateRange started with startDate: {} and endDate: {}", startDate, endDate);

        List<Appointment> appointments = appointmentRepository.findByAppointmentDateBetween(startDate, endDate);
        log.info("AppointmentService::getAppointmentsByDateRange appointments: {}", appointments);

        return appointmentMapper.mapToAppointmentResponseDtoList(appointments);
    }

    // Randevu durumuna göre randevuları al
    public List<AppointmentResponseDto> getAppointmentsByStatus(String status) {
        log.info("AppointmentService::getAppointmentsByStatus started with status: {}", status);

        List<Appointment> appointments = appointmentRepository.findByStatus(status);
        log.info("AppointmentService::getAppointmentsByStatus appointments: {}", appointments);

        return appointmentMapper.mapToAppointmentResponseDtoList(appointments);
    }

    // Randevu tarihine göre duruma göre randevuları al
    public List<AppointmentResponseDto> getAppointmentsByStatusAndDate(String status, LocalDateTime appointmentDate) {
        log.info("AppointmentService::getAppointmentsByStatusAndDate started with status: {} and appointmentDate: {}", status, appointmentDate);

        List<Appointment> appointments = appointmentRepository.findByStatusAndAppointmentDate(status, appointmentDate);
        log.info("AppointmentService::getAppointmentsByStatusAndDate appointments: {}", appointments);

        return appointmentMapper.mapToAppointmentResponseDtoList(appointments);
    }

    // Son güncellenme tarihinden sonra güncellenmiş randevuları al
    public List<AppointmentResponseDto> getUpdatedAppointmentsAfter(LocalDateTime updatedAt) {
        log.info("AppointmentService::getUpdatedAppointmentsAfter started with updatedAt: {}", updatedAt);

        List<Appointment> appointments = appointmentRepository.findByUpdatedAtAfter(updatedAt);
        log.info("AppointmentService::getUpdatedAppointmentsAfter appointments: {}", appointments);

        return appointmentMapper.mapToAppointmentResponseDtoList(appointments);
    }

    // Randevu ID'sine göre bul
    public AppointmentResponseDto getAppointmentById(String id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        return appointmentMapper.mapToAppointmentResponseDto(appointment);
    }

    // Belirli bir veteriner için tüm randevuları al
    public List<AppointmentResponseDto> getAppointmentsByVetId(String vetId) {
        log.info("AppointmentService::getAppointmentsByVetId started with vetId: {}", vetId);

        List<Appointment> appointments = appointmentRepository.findByVetId(vetId);
        log.info("AppointmentService::getAppointmentsByVetId appointments: {}", appointments);

        return appointmentMapper.mapToAppointmentResponseDtoList(appointments);
    }

    // Belirli bir pet için tüm randevuları al
    public List<AppointmentResponseDto> getAppointmentsByPetId(String petId) {
        log.info("AppointmentService::getAppointmentsByPetId started with petId: {}", petId);

        List<Appointment> appointments = appointmentRepository.findByPetId(petId);
        log.info("AppointmentService::getAppointmentsByPetId appointments: {}", appointments);

        return appointmentMapper.mapToAppointmentResponseDtoList(appointments);
    }

    // Randevu güncelle
    @Transactional
    public AppointmentResponseDto updateAppointment(String id, AppointmentRequestDto appointmentRequestDto) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        // Güncellemeleri uygula
        existingAppointment.setPetId(appointmentRequestDto.getPetId());
        existingAppointment.setVetId(appointmentRequestDto.getVetId());
        existingAppointment.setAppointmentDate(appointmentRequestDto.getAppointmentDate());
        existingAppointment.setReason(appointmentRequestDto.getReason());
        existingAppointment.setStatus(appointmentRequestDto.getStatus());
        existingAppointment.setUpdatedAt(LocalDateTime.now());

        Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
        return appointmentMapper.mapToAppointmentResponseDto(updatedAppointment);
    }

    // Randevu sil
    public void deleteAppointment(String id) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        appointmentRepository.delete(existingAppointment);
    }

}
