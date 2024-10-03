package com.example.billing_service.repository;

import com.example.billing_service.model.Billing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillingRepository  extends MongoRepository<Billing,String> {

    // Tüm faturalara göre duruma göre sorgu
    List<Billing> findByStatus(String status);

    // Belirli bir sahibin tüm faturalarını alma
    List<Billing> findByOwnerId(String ownerId);

    // Belirli bir randevuya göre faturaları alma
    List<Billing> findByAppointmentId(String appointmentId);

    // Ödeme tarihine göre faturaları alma
    List<Billing> findByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Güncellenme tarihine göre sorgulama
    List<Billing> findByUpdatedAtAfter(LocalDateTime updatedAt);

    // Durum ve ödeme tarihi aralığına göre sorgulama
    @Query("{ 'status': ?0, 'paymentDate': { $gte: ?1, $lte: ?2 } }")
    List<Billing> findByStatusAndPaymentDateBetween(String status, LocalDateTime startDate, LocalDateTime endDate);
}
