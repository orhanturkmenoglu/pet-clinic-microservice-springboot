package com.example.appointment_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(getInfo()
                        .contact(contactInfo()));
    }

    @Bean
    public Info getInfo() {
        return new Info()
                .title("Appointment Service API")
                .description("API for managing pets")
                .version("1.0.0");
    }

    @Bean
    public Contact contactInfo() {
        return new Contact()
                .name("ORHAN TÜRKMENOĞLU")
                .email("orhantrkmn749@gmail.com");
    }
}
