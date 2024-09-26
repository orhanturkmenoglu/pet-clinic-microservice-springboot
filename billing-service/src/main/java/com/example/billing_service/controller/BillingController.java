package com.example.billing_service.controller;

import com.example.billing_service.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/billings")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;
}
