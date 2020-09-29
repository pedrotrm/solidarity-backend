package com.solidarity.api;

import com.solidarity.api.config.SolidarityProperty;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SolidarityProperty.class)
public class SolidarityAlphaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SolidarityAlphaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
