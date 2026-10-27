package com.hangoutplanner.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.hangoutplanner.pojo.Activity;

/**
 * Repository interface for accessing and managing Activity documents
 * in the MongoDB database.
 *
 * <p>Extends {@link MongoRepository} to inherit built-in CRUD operations,
 * including saving, retrieving, updating, and deleting activities.
 * Spring Data MongoDB automatically provides the implementation at
 * runtime, so a custom implementation is not required for these
 * standard operations.
 *
 * <p>The repository uses {@link Activity} as its document type and
 * {@link String} as the type of its identifier.
 *
 * @see Activity
 * @see MongoRepository
 */
public interface ActivityRepository extends MongoRepository<Activity, String> {

}