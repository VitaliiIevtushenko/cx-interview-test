package com.transportexchangegroup.fm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Configuration
public class EmbeddedPostgresConfig {

    @Bean
    public DataSource dataSource(PostgreSQLContainer pc) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl(pc.getJdbcUrl());
        driverManagerDataSource.setUsername(pc.getUsername());
        driverManagerDataSource.setPassword(pc.getPassword());

        return driverManagerDataSource;
    }

    @Bean
    public PostgreSQLContainer postgreSQLContainer() {
        PostgreSQLContainer pc = new PostgreSQLContainer("postgres:9.6");
        pc.start();
        return pc;
    }
}