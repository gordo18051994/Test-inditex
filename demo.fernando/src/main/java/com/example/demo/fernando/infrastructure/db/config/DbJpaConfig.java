package com.example.demo.fernando.infrastructure.db.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ConfigurationProperties("spring.datasource")
@EntityScan(basePackages = "com.example.demo.fernando.infrastructure.db.entity")
@EnableJpaRepositories(
        basePackages = "com.example.demo.fernando.infrastructure.db.repository")
public class DbJpaConfig {

}