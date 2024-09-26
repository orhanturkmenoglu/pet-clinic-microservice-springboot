package com.example.billing_service.repository;

import com.example.billing_service.model.Billing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository  extends MongoRepository<Billing,String> {
}
