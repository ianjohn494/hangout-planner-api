package com.hangoutplanner.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "messages")
public class Message {

    @Id
    private String id;

    private String hangoutId;

    private String senderId;

    private String content;

    private LocalDateTime sentAt;

    private boolean edited;
}