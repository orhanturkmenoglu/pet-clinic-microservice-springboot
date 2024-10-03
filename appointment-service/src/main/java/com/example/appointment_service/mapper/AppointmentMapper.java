package com.example.appointment_service.mapper;

import com.example.appointment_service.dto.AppointmentRequestDto;
import com.example.appointment_service.dto.AppointmentResponseDto;
import com.example.appointment_service.model.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {

    // AppointmentRequestDto'dan Appointment'a dönüşüm
    public Appointment mapToAppointment(AppointmentRequestDto appointmentRequestDto) {
        if (appointmentRequestDto == null) {
            return null; // Null kontrolü
        }

        return Appointment.builder()
                .petId(appointmentRequestDto.getPetId())
                .vetId(appointmentRequestDto.getVetId())
                .appointmentDate(appointmentRequestDto.getAppointmentDate())
                .reason(appointmentRequestDto.getReason())
                .status(appointmentRequestDto.getStatus())
                .updatedAt(LocalDateTime.now()) // Güncelleme tarihi olarak mevcut zaman
                .build();
    }

    // Appointment'tan AppointmentResponseDto'ya dönüşüm
    public AppointmentResponseDto mapToAppointmentResponseDto(Appointment savedAppointment) {
        if (savedAppointment == null) {
            return null; // Null kontrolü
        }

        return AppointmentResponseDto.builder()
                .id(savedAppointment.getId())
                .petId(savedAppointment.getPetId())
                .vetId(savedAppointment.getVetId())
                .appointmentDate(savedAppointment.getAppointmentDate())
                .reason(savedAppointment.getReason())
                .status(savedAppointment.getStatus())
                .updatedAt(savedAppointment.getUpdatedAt())
                .build();
    }

    // List<Appointment>'dan List<AppointmentResponseDto>'ya dönüşüm
    public List<AppointmentResponseDto> mapToAppointmentResponseDtoList(List<Appointment> appointments) {
        if (appointments == null || appointments.isEmpty()) {
            return List.of(); // Null veya boş liste kontrolü
        }

        return appointments.stream()
                .map(this::mapToAppointmentResponseDto)
                .filter(Objects::nonNull) // Null dönüşümlerini filtrele
                .collect(Collectors.toList());
    }
}
