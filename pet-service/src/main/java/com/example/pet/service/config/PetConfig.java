package com.example.pet.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PetConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
