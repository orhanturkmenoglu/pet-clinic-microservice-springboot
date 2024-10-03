package com.example.owner_service.repository;

import com.example.owner_service.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, String> {

    // Adına göre sahipleri bul
    List<Owner> findByFirstNameContainingIgnoreCase(String firstName);

    // Soyadına göre sahipleri bul
    List<Owner> findByLastNameContainingIgnoreCase(String lastName);

    // Telefon numarasına göre sahip bul
    Optional<Owner> findByPhoneNumber(String phoneNumber);

    // E-posta adresine göre sahip bul
    Optional<Owner> findByEmail(String email);

    // Belirli bir tarih aralığında sahipleri bul
    List<Owner> findByOwnerDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Güncellenme tarihine göre sahipleri bul
    List<Owner> findByUpdatedAtAfter(LocalDateTime date);
}