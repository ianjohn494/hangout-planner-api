package com.hangoutplanner.pojo;

import com.hangoutplanner.enums.MemberRole;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HangoutMember {

    private String userId;

    private MemberRole role;

    private LocalDateTime joinedAt;
}