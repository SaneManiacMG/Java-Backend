package com.g4l.timesheet_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.g4l.timesheet_backend.models.entities.Role;
import com.g4l.timesheet_backend.models.enums.AccountType;
import com.g4l.timesheet_backend.repositories.UserAuthoritiesRepository;

@SpringBootApplication
public class TimesheetBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserAuthoritiesRepository userAuthoritiesRepository) {
		return args -> {
			int numOfRoles = AccountType.values().length;
			
			if (userAuthoritiesRepository.count() != numOfRoles) {
				userAuthoritiesRepository.deleteAll();
				for (AccountType accountType : AccountType.values()) {
					userAuthoritiesRepository.save(new Role("ROLE_" + accountType.name()));
				}
			}
		};
	}
}
