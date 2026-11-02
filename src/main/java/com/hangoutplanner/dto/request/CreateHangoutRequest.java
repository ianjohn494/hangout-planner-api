package com.hangoutplanner.dto.request;

public record CreateHangoutRequest(
        String name,
        String description
) {}