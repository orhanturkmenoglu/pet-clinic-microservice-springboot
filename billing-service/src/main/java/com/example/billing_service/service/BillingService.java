package com.example.billing_service.service;

import com.example.billing_service.repository.BillingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillingService {

    private BillingRepository billingRepository;
}
