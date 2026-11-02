package com.hangoutplanner.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "hangouts")
public class Hangout {

    @Id
    private String id;

    private String name;

    private String description;

    private String adminId;

    private List<HangoutMember> members = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}