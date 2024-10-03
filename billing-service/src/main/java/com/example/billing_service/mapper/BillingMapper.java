package com.example.billing_service.mapper;

import com.example.billing_service.dto.BillingRequestDto;
import com.example.billing_service.dto.BillingResponseDto;
import com.example.billing_service.model.Billing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BillingMapper {

    public BillingResponseDto mapToBillingResponseDto(Billing billing) {
        if (billing == null) {
            return null;
        }

        return BillingResponseDto.builder()
                .id(billing.getId())
                .ownerId(billing.getOwnerId())
                .appointmentId(billing.getAppointmentId())
                .amount(billing.getAmount())
                .status(billing.getStatus())
                .paymentDate(billing.getPaymentDate())
                .updatedAt(billing.getUpdatedAt())
                .build();
    }

    public Billing mapToBilling(BillingRequestDto billingRequestDto) {
        if (billingRequestDto == null) {
            return null;
        }

        return Billing.builder()
                .ownerId(billingRequestDto.getOwnerId())
                .appointmentId(billingRequestDto.getAppointmentId())
                .amount(billingRequestDto.getAmount())
                .status(billingRequestDto.getStatus())
                .build();
    }

    public List<BillingResponseDto> mapToBillingResponseDtoList(List<Billing> billings) {
        return billings.stream()
                .map(this::mapToBillingResponseDto)
                .collect(Collectors.toList());
    }
}
