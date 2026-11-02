package com.hangoutplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * Entry point for the Hangout Planner API application.
 *
 * <p>
 * Configures the application using Spring Boot's autoconfiguration
 * and component scanning. Spring Boot scans this package and its
 * subpackages for components such as controllers, services, and
 * repositories.
 *
 * <p>
 * Starting this application initializes the Spring application
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
	 * <p>
	 * Bootstraps Spring Boot, initializes the application context,
	 * and starts the embedded web server.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(HangoutPlannerApplication.class, args);
	}

	@Bean
	CommandLineRunner checkMongoConfig(Environment env) {
		return args -> {
			String uri = env.getProperty("spring.data.mongodb.uri");

			System.out.println("=== MONGO CONFIG CHECK ===");
			System.out.println("Mongo URI present: " + (uri != null && !uri.isBlank()));
			System.out.println("Mongo URI starts with mongodb+srv://: "
					+ (uri != null && uri.startsWith("mongodb+srv://")));
			System.out.println("==========================");
		};
	}
}
