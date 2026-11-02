package com.hangoutplanner.service;

import com.hangoutplanner.pojo.Hangout;

import java.util.List;
import java.util.Optional;

public interface HangoutService {

    Hangout createHangout(Hangout hangout, String creatorId);

    Optional<Hangout> getHangoutById(String id);

    List<Hangout> getAllHangouts();

    List<Hangout> getHangoutsByUserId(String userId);

    Hangout addMember(String hangoutId, String userId, String requestingUserId);

    Hangout removeMember(String hangoutId, String userId, String requestingUserId);

    Hangout transferAdmin(String hangoutId, String newAdminId, String requestingUserId);

    void deleteHangout(String hangoutId, String requestingUserId);
}