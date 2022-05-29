package org.ennea.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaxxer.hikari.HikariConfig;

@Configuration
@EntityScan(basePackages = {"org.ennea.model"})
@EnableJpaRepositories(basePackages = {
		"org.ennea.repository"
})
public class EnneaDBConfig {
	
	@Bean
	public HikariConfig config() {
		HikariConfig hikariConfig = new HikariConfig();
		
		hikariConfig.addDataSourceProperty("socketTimeout", 600000);
		hikariConfig.setMaxLifetime(600000);
		return hikariConfig;
	}
	
}
