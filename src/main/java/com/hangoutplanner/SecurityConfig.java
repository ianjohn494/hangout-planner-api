package com.hangoutplanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures HTTP security for the Hangout Planner API.
 *
 * <p>Defines the application's security filter chain, allowing unauthenticated
 * access to the health check endpoint at {@code /health/api/health} while
 * requiring authentication for all other HTTP requests.
 *
 * <p>Enables HTTP Basic authentication for protected endpoints.
 *
 * @see org.springframework.security.web.SecurityFilterChain
 * @see org.springframework.security.config.annotation.web.builders.HttpSecurity
 */
@Configuration
public class SecurityConfig {

    /**
     * Creates the security filter chain that controls access to API endpoints.
     *
     * @param http the HTTP security configuration used to define authorization
     *             rules and authentication mechanisms
     * @return the configured security filter chain
     * @throws Exception if the security configuration cannot be built
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/health/api/health").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
