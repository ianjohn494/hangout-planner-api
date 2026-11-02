package com.hangoutplanner.repository;

import com.hangoutplanner.pojo.Hangout;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HangoutRepository extends MongoRepository<Hangout, String> {

    List<Hangout> findByAdminId(String adminId);

    List<Hangout> findByMembersUserId(String userId);
}