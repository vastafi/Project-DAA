package com.prescriptionservice.prescriptionservice;

import com.prescriptionservice.prescriptionservice.config.TrustStoreConfig;
import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrescriptionserviceApplication {
	@Autowired
	TrustStoreConfig trustStoreConfig;
	@Autowired
	private Flyway flyway;

	public static void main(String[] args) {
		SpringApplication.run(PrescriptionserviceApplication.class, args);
	}
	@PostConstruct
	public void configBeforeAppRun() {
		trustStoreConfig.configTrustStore();
	}

}
