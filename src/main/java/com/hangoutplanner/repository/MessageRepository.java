package com.hangoutplanner.repository;

import com.hangoutplanner.pojo.Message;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findByHangoutIdOrderBySentAtAsc(String hangoutId);
}