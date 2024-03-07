package com.g4l.timesheet_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimesheetBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetBackendApplication.class, args);
	}

	// @Bean
	// CommandLineRunner run(UserAuthoritiesRepository userAuthoritiesRepository) {
	// 	return args -> {
	// 		int numOfRoles = AccountRole.values().length;
			
	// 		if (userAuthoritiesRepository.count() != numOfRoles) {
	// 			userAuthoritiesRepository.deleteAll();
	// 			for (AccountRole accountType : AccountRole.values()) {
	// 				userAuthoritiesRepository.save(new Role("ROLE_" + accountType.name()));
	// 			}
	// 		}
	// 	};
	// }
}
