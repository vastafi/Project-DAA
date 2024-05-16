package com.prescriptionservice.prescriptionservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.HttpsURLConnection;

@Slf4j
@Configuration
public class TrustStoreConfig {

    @Value("${auth-server.trust-store.jks}")
    private String trustStoreFile;

    @Value("${auth-server.trust-store.password}")
    private String password;

    @Autowired
    private Environment environment;


    public void configTrustStore() {
        try {
            //For skip SAN problems with self sign cert.
            HttpsURLConnection.setDefaultHostnameVerifier(
                    (hostname, sslSession) -> true);

            String trustStorePath;
            String auth_server_trust_store_path = environment.getProperty("AUTH_SERVER_TRUST_STORE_PATH");
            log.info("Configure trust store for auth server. AUTH_SERVER_TRUST_STORE_PATH value: {}", auth_server_trust_store_path);
            if (auth_server_trust_store_path != null) {
                trustStorePath = auth_server_trust_store_path;
            } else {
                trustStorePath = new ClassPathResource(trustStoreFile).getFile().getAbsolutePath();
            }


            log.info("Configure trust store for auth server. File path: {}", trustStorePath);

            System.getProperties().setProperty("javax.net.ssl.trustStore", trustStorePath);
            System.getProperties().setProperty("javax.net.ssl.trustStorePassword", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
