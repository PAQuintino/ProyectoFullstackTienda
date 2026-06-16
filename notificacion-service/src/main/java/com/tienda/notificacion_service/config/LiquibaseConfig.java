package com.tienda.notificacion_service.config;

import liquibase.integration.spring.SpringLiquibase;
import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class LiquibaseConfig {
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/db.changelog.sql");
        liquibase.setShouldRun(true);
        return liquibase;                
    }
}