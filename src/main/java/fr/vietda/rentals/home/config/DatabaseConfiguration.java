package fr.vietda.rentals.home.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"fr.vietda.rentals.home.model"})
@EnableJpaRepositories(basePackages = {"fr.vietda.rentals.home.repository"})
@ComponentScan(basePackages = {"fr.vietda.rentals"})
@EnableTransactionManagement
public class DatabaseConfiguration {
}
