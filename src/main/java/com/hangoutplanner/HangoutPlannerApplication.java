package com.hangoutplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Hangout Planner API application.
 *
 * <p>Configures the application using Spring Boot's autoconfiguration
 * and component scanning. Spring Boot scans this package and its
 * subpackages for components such as controllers, services, and
 * repositories.
 *
 * <p>Starting this application initializes the Spring application
 * context and launches the embedded web server to handle HTTP requests.
 *
 * @see SpringBootApplication
 * @see SpringApplication
 */
@SpringBootApplication
public class HangoutPlannerApplication {

	/**
	 * Starts the Hangout Planner API application.
	 *
	 * <p>Bootstraps Spring Boot, initializes the application context,
	 * and starts the embedded web server.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(HangoutPlannerApplication.class, args);
	}
}
