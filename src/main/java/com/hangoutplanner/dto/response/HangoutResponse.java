package com.hangoutplanner.dto.response;

import com.hangoutplanner.pojo.HangoutMember;

import java.time.LocalDateTime;
import java.util.List;

public record HangoutResponse(
        String id,
        String name,
        String description,
        String adminId,
        List<HangoutMember> members,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}