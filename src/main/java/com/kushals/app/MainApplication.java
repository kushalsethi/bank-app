package com.kushals.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.kushals.repository.AccountRepository;

@EnableJpaRepositories(basePackageClasses = { AccountRepository.class })
@EntityScan("com.kushals.*")
@SpringBootApplication(scanBasePackages = { "com.kushals.*" })
public class MainApplication {
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
