package ru.alishev.springcourse.Project2Boot;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import shpilevsky.Installer;

@SpringBootApplication
@EnableTransactionManagement
public class Project2BootApplication {

	public static void main(String[] args) {

		SpringApplication.run(Project2BootApplication.class, args);

	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	@Bean
//	public Installer installer() {
//		return new Installer();
//	}

}
