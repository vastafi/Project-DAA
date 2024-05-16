package com.stockservice.stockservice;

import com.stockservice.stockservice.config.TrustStoreConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockserviceApplication {
	@Autowired
	TrustStoreConfig trustStoreConfig;

	public static void main(String[] args) {
		SpringApplication.run(StockserviceApplication.class, args);
	}

	@PostConstruct
	public void configBeforeAppRun() {
		trustStoreConfig.configTrustStore();
	}
}
