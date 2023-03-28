package com.skewwhiffy.auraltester;

import com.skewwhiffy.auraltester.dao.Info;
import com.skewwhiffy.auraltester.repository.InfoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuraltesterApplication {
	private static final Logger log = LoggerFactory.getLogger(AuraltesterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AuraltesterApplication.class);
	}

	@Bean
	public CommandLineRunner demo(InfoRepository repository) {
		return (args) -> {
			repository.save(new Info("0.0.1-java"));
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(it -> log.info(it.toString()));
		};
	}

}
