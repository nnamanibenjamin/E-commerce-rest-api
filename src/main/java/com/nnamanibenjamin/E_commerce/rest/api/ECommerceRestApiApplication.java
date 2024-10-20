package com.nnamanibenjamin.E_commerce.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EntityScan(basePackages = {"com.nnamanibenjamin.E_commerce.rest.api.model"})
@EnableJpaRepositories(basePackages = {"com.nnamanibenjamin.E_commerce.rest.api.repository"})
public class ECommerceRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceRestApiApplication.class, args);
	}

}
