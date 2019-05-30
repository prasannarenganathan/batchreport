package com.cnasurety.extagencyint.batches.ivans.reporting;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "reportingEntityManagerFactory", transactionManagerRef = "reportingTransactionManager", basePackages = {
        "com.cnasurety.extagencyint.batches.ivans.reporting" })
public class ReportingDBConfig {

    @Bean(name = "reportingDataSource")
    @ConfigurationProperties(prefix = "report.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "reportingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("reportingDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.cnasurety.extagencyint.batches.ivans.reporting")
                .persistenceUnit("reporting").build();
    }

    @Bean(name = "reportingTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("reportingEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}