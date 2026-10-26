package com.hangoutplanner.controller;

import com.hangoutplanner.pojo.Activity;
import com.hangoutplanner.repository.ActivityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityController {

    private final ActivityRepository repository;

    public ActivityController(ActivityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return repository.findAll();
    }
}