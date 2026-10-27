package com.hangoutplanner.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents an activity that users can consider when planning a hangout.
 *
 * <p>Stores activity information, including its unique identifier, name,
 * category, and estimated cost. This class is mapped to the MongoDB
 * {@code activities} collection using Spring Data MongoDB.
 *
 * <p>Lombok annotations generate getter and setter methods for selected
 * fields, while Spring Data uses the {@code id} field to identify
 * documents in the collection.
 *
 * @see org.springframework.data.mongodb.core.mapping.Document
 */
@Document(collection = "activities")
public class Activity {

    @Getter
    @Id
    private String id;

    @Getter
    @Setter
    private String name;

    @Setter
    @Getter
    private String category;

    @Setter
    private Integer estimatedCost;

    /**
     * Creates an empty Activity object.
     *
     * <p>This constructor is provided for frameworks such as Spring Data
     * MongoDB that may need to instantiate an object before populating
     * its fields.
     */
    public Activity() {
    }

    /**
     * Creates an Activity with the specified name, category, and estimated cost.
     *
     * @param name the name of the activity
     * @param category the category the activity belongs to
     * @param estimatedCost the estimated cost of the activity
     */
    public Activity(String name, String category, Integer estimatedCost) {
        this.name = name;
        this.category = category;
        this.estimatedCost = estimatedCost;
    }

    /**
     * Returns the estimated cost of the activity.
     *
     * @return the estimated cost as a double
     */
    public Integer getEstimatedCost() {
        return estimatedCost;
    }

}
