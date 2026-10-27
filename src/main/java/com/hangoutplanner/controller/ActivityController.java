package com.hangoutplanner.controller;

import com.hangoutplanner.pojo.Activity;
import com.hangoutplanner.repository.ActivityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing activities in the Hangout Planner API.
 *
 * <p>Exposes HTTP endpoints under {@code /api/activities} for retrieving
 * activity data. Allows cross-origin requests from the local frontend
 * running at {@code http://localhost:4200}.
 *
 * <p>Uses {@link ActivityRepository} to access activity data and return
 * results to clients as JSON responses.
 *
 * @see Activity
 * @see ActivityRepository
 */
@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityController {

    private final ActivityRepository repository;

    /**
     * Creates an ActivityController with the specified repository.
     *
     * @param repository the repository used to retrieve and manage activity data
     */
    public ActivityController(ActivityRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all activities stored in the repository.
     *
     * <p>Handles HTTP GET requests to {@code /api/activities} and returns
     * the activities as a JSON array.
     *
     * @return a list containing all activities returned by the repository;
     *         may be empty if no activities exist
     */
    @GetMapping
    public List<Activity> getAllActivities() {
        return repository.findAll();
    }
}