package com.hangoutplanner.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Provides a health check endpoint for the Hangout Planner API.
 *
 * <p>Exposes a REST endpoint at {@code /health/api/health} that returns
 * the application's current status and service name as a JSON response.
 * This endpoint can be used to verify that the Spring Boot application
 * is running and responding to HTTP requests.
 *
 * <p>To test the endpoint locally, run:
 * {@code curl -i http://localhost:{port number}/health/api/health}
 *
 * <p>Note: This endpoint confirms that the application can respond to
 * requests but does not verify database connectivity or the health of
 * other dependent services.
 *
 * @see org.springframework.web.bind.annotation.RestController
 * @see org.springframework.web.bind.annotation.RequestMapping
 */
@RestController
@RequestMapping("/health")
public class HealthCheck {
    /**
     * Returns the health status of the Hangout Planner API.
     *
     * <p>Responds to HTTP GET requests at {@code /health/api/health}
     * with a JSON object containing the status and service name.
     *
     * @return a map containing {@code status} with the value {@code UP}
     *         and {@code service} with the value {@code hangout-planner-api}
     */
    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "service", "hangout-planner-api"
        );
    }
}
