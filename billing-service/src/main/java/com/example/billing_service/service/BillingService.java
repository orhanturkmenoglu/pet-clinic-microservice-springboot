package com.example.billing_service.service;

import com.example.billing_service.dto.BillingRequestDto;
import com.example.billing_service.dto.BillingResponseDto;
import com.example.billing_service.mapper.BillingMapper;
import com.example.billing_service.model.Billing;
import com.example.billing_service.repository.BillingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillingService {

    private final BillingRepository billingRepository;

    private final BillingMapper billingMapper; // DTO'ları dönüştürmek için bir mapper

    // Fatura oluştur
    @Transactional
    public BillingResponseDto createBilling(BillingRequestDto billingRequestDto) {
        log.info("BillingService::createBilling started");

        Billing billing = billingMapper.mapToBilling(billingRequestDto);
        Billing savedBilling = billingRepository.save(billing);

        log.info("BillingService::createBilling savedBilling: {}", savedBilling);
        log.info("BillingService::createBilling finished");
        return billingMapper.mapToBillingResponseDto(savedBilling);
    }

    // Tüm faturaları bul
    public List<BillingResponseDto> getAllBillings() {
        log.info("BillingService::getAllBillings started");

        List<Billing> billings = billingRepository.findAll();
        log.info("BillingService::getAllBillings billings: {}", billings);

        if (billings.isEmpty()) {
            throw new RuntimeException("No billings found");
        }

        log.info("BillingService::getAllBillings finished");
        return billingMapper.mapToBillingResponseDtoList(billings);
    }

    // Fatura ID'sine göre bul
    public BillingResponseDto getBillingById(String id) {
        log.info("BillingService::getBillingById started with id: {}", id);

        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found with id: " + id));

        log.info("BillingService::getBillingById billing: {}", billing);
        log.info("BillingService::getBillingById finished");
        return billingMapper.mapToBillingResponseDto(billing);
    }



    // Duruma göre faturaları al
    public List<BillingResponseDto> getBillingsByStatus(String status) {
        log.info("BillingService::getBillingsByStatus started with status: {}", status);

        List<Billing> billings = billingRepository.findByStatus(status);
        log.info("BillingService::getBillingsByStatus billings: {}", billings);

        log.info("BillingService::getBillingsByStatus finished");
        return billingMapper.mapToBillingResponseDtoList(billings);
    }

    // Belirli bir sahibin faturalarını al
    public List<BillingResponseDto> getBillingsByOwnerId(String ownerId) {
        log.info("BillingService::getBillingsByOwnerId started with ownerId: {}", ownerId);

        List<Billing> billings = billingRepository.findByOwnerId(ownerId);
        log.info("BillingService::getBillingsByOwnerId billings: {}", billings);

        log.info("BillingService::getBillingsByOwnerId finished");
        return billingMapper.mapToBillingResponseDtoList(billings);
    }

    // Belirli bir randevuya göre faturaları al
    public List<BillingResponseDto> getBillingsByAppointmentId(String appointmentId) {
        log.info("BillingService::getBillingsByAppointmentId started with appointmentId: {}", appointmentId);

        List<Billing> billings = billingRepository.findByAppointmentId(appointmentId);
        log.info("BillingService::getBillingsByAppointmentId billings: {}", billings);

        log.info("BillingService::getBillingsByAppointmentId finished");
        return billingMapper.mapToBillingResponseDtoList(billings);
    }

    // Ödeme tarihine göre faturaları al
    public List<BillingResponseDto> getBillingsByPaymentDate(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("BillingService::getBillingsByPaymentDate started");

        List<Billing> billings = billingRepository.findByPaymentDateBetween(startDate, endDate);
        log.info("BillingService::getBillingsByPaymentDate billings: {}", billings);

        log.info("BillingService::getBillingsByPaymentDate finished");
        return billingMapper.mapToBillingResponseDtoList(billings);
    }

    // Güncellenme tarihine göre sorgulama
    public List<BillingResponseDto> getUpdatedBillingsAfter(LocalDateTime updatedAt) {
        log.info("BillingService::getUpdatedBillingsAfter started");

        List<Billing> billings = billingRepository.findByUpdatedAtAfter(updatedAt);
        log.info("BillingService::getUpdatedBillingsAfter billings: {}", billings);

        log.info("BillingService::getUpdatedBillingsAfter finished");
        return billingMapper.mapToBillingResponseDtoList(billings);
    }

    // Durum ve ödeme tarihi aralığına göre sorgulama
    public List<BillingResponseDto> getBillingsByStatusAndPaymentDate(String status, LocalDateTime startDate, LocalDateTime endDate) {
        log.info("BillingService::getBillingsByStatusAndPaymentDate started");

        List<Billing> billings = billingRepository.findByStatusAndPaymentDateBetween(status, startDate, endDate);
        log.info("BillingService::getBillingsByStatusAndPaymentDate billings: {}", billings);

        log.info("BillingService::getBillingsByStatusAndPaymentDate finished");
        return billingMapper.mapToBillingResponseDtoList(billings);
    }

    // Fatura güncelle
    @Transactional
    public BillingResponseDto updateBilling(String id, BillingRequestDto billingRequestDto) {
        log.info("BillingService::updateBilling started with id: {}", id);

        Billing existingBilling = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found with id: " + id));

        log.info("BillingService::updateBilling existingBilling: {}", existingBilling);

        // Güncellemeleri uygula
        existingBilling.setOwnerId(billingRequestDto.getOwnerId());
        existingBilling.setAppointmentId(billingRequestDto.getAppointmentId());
        existingBilling.setAmount(billingRequestDto.getAmount());
        existingBilling.setStatus(billingRequestDto.getStatus());
        existingBilling.setUpdatedAt(LocalDateTime.now());

        Billing updatedBilling = billingRepository.save(existingBilling);
        log.info("BillingService::updateBilling updatedBilling: {}", updatedBilling);
        log.info("BillingService::updateBilling finished");
        return billingMapper.mapToBillingResponseDto(updatedBilling);
    }


    // Fatura sil
    public void deleteBilling(String id) {
        log.info("BillingService::deleteBilling started with id: {}", id);

        Billing existingBilling = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found with id: " + id));

        log.info("BillingService::deleteBilling existingBilling: {}", existingBilling);
        billingRepository.delete(existingBilling);

        log.info("BillingService::deleteBilling finished");
    }
}
