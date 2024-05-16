package com.drugdiscovery.drugdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableEurekaServer
public class DrugdiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrugdiscoveryApplication.class, args);
	}

}
