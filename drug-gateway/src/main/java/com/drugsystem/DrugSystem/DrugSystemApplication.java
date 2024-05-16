package com.drugsystem.DrugSystem;

import com.drugsystem.DrugSystem.config.TrustStoreConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrugSystemApplication {
	@Autowired
	TrustStoreConfig trustStoreConfig;

	public static void main(String[] args) {
		SpringApplication.run(DrugSystemApplication.class, args);
	}
	@PostConstruct
	public void configBeforeAppRun() {
		trustStoreConfig.configTrustStore();
	}

}
