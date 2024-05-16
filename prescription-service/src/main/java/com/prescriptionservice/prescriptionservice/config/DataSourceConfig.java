package com.prescriptionservice.prescriptionservice.config;

import com.prescriptionservice.prescriptionservice.enums.DataSourceType;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    @Autowired
    Environment env;

    @Bean
    @ConfigurationProperties(prefix = "spring.write.datasource")
    public DataSource masterDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.write.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.write.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.write.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.write.datasource.password"));
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.read.datasource")
    public DataSource slaveDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.read.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.read.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.read.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.read.datasource.password"));
        return dataSource;
    }

    @Bean
    public DataSource routingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.getType(), masterDataSource);
        targetDataSources.put(DataSourceType.SLAVE.getType(), slaveDataSource);
        dataSource.setTargetDataSources(targetDataSources);
        return dataSource;
    }

    @Primary
    @Bean
    public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}
