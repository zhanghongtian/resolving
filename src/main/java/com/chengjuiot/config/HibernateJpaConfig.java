package com.chengjuiot.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = "com.chengjuiot.domain")
@EnableJpaRepositories(basePackages = "com.chengjuiot.dao")
@EnableTransactionManagement
public class HibernateJpaConfig {
}
