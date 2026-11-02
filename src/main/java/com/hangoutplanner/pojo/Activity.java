package com.hangoutplanner.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "activities")
public class Activity {
    @Id
    private String id;
    private String name;
    private String category;
    private Integer estimatedCost;

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
}
