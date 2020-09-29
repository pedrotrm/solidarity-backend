package com.solidarity.api.config;

import com.solidarity.api.services.EmailService;
import com.solidarity.api.services.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }

}
