package com.laa66.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.laa66")
@EnableTransactionManagement
public class DatabaseConfig {
    // Configuration is handled by Spring Boot autoconfiguration via application.properties
    // This class exists to enable JPA repositories and transaction management
}