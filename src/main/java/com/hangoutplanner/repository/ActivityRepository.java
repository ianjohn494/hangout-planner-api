package com.hangoutplanner.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hangoutplanner.pojo.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String> {

}