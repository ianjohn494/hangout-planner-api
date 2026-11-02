package com.hangoutplanner.service.impl;

import com.hangoutplanner.enums.MemberRole;
import com.hangoutplanner.pojo.Hangout;
import com.hangoutplanner.pojo.HangoutMember;
import com.hangoutplanner.repository.HangoutRepository;
import com.hangoutplanner.repository.UserRepository;
import com.hangoutplanner.service.HangoutService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HangoutServiceImpl implements HangoutService {

    private final HangoutRepository hangoutRepository;
    private final UserRepository userRepository;

    @Override
    public Hangout createHangout(Hangout hangout, String creatorId) {

        if (creatorId == null || creatorId.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Creator ID is required."
            );
        }

        if (!userRepository.existsById(creatorId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Creator not found."
            );
        }

        HangoutMember admin = new HangoutMember();

        admin.setUserId(creatorId);
        admin.setRole(MemberRole.ADMIN);
        admin.setJoinedAt(LocalDateTime.now());

        hangout.setAdminId(creatorId);
        hangout.setMembers(new ArrayList<>(List.of(admin)));

        hangout.setCreatedAt(LocalDateTime.now());
        hangout.setUpdatedAt(LocalDateTime.now());

        log.info("Creating Hangout: {}", hangout.getName());

        return hangoutRepository.save(hangout);
    }

    @Override
    public Optional<Hangout> getHangoutById(String id) {
        return hangoutRepository.findById(id);
    }

    @Override
    public List<Hangout> getAllHangouts() {
        return hangoutRepository.findAll();
    }

    @Override
    public List<Hangout> getHangoutsByUserId(String userId) {
        return hangoutRepository.findByMembersUserId(userId);
    }

    @Override
    public Hangout addMember(
            String hangoutId,
            String userId,
            String requestingUserId) {

        Hangout hangout = getExistingHangout(hangoutId);

        requireAdmin(hangout, requestingUserId);

        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found."
            );
        }

        boolean alreadyMember = hangout.getMembers().stream()
                .anyMatch(member -> member.getUserId().equals(userId));

        if (alreadyMember) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User is already a member."
            );
        }

        HangoutMember member = new HangoutMember();

        member.setUserId(userId);
        member.setRole(MemberRole.MEMBER);
        member.setJoinedAt(LocalDateTime.now());

        hangout.getMembers().add(member);

        updateTimestamp(hangout);

        return hangoutRepository.save(hangout);
    }

    @Override
    public Hangout removeMember(
            String hangoutId,
            String userId,
            String requestingUserId) {

        Hangout hangout = getExistingHangout(hangoutId);

        requireAdmin(hangout, requestingUserId);

        if (hangout.getAdminId().equals(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The admin cannot be removed."
            );
        }

        boolean removed = hangout.getMembers()
                .removeIf(member -> member.getUserId().equals(userId));

        if (!removed) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Member not found."
            );
        }

        updateTimestamp(hangout);

        return hangoutRepository.save(hangout);
    }

    @Override
    public Hangout transferAdmin(
            String hangoutId,
            String newAdminId,
            String requestingUserId) {

        Hangout hangout = getExistingHangout(hangoutId);

        requireAdmin(hangout, requestingUserId);

        HangoutMember newAdmin = hangout.getMembers().stream()
                .filter(member -> member.getUserId().equals(newAdminId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "New admin must already be a member."
                ));

        hangout.getMembers().forEach(member ->
                member.setRole(MemberRole.MEMBER)
        );

        newAdmin.setRole(MemberRole.ADMIN);
        hangout.setAdminId(newAdminId);

        updateTimestamp(hangout);

        return hangoutRepository.save(hangout);
    }

    @Override
    public void deleteHangout(
            String hangoutId,
            String requestingUserId) {

        Hangout hangout = getExistingHangout(hangoutId);

        requireAdmin(hangout, requestingUserId);

        hangoutRepository.delete(hangout);

        log.info("Deleted Hangout: {}", hangoutId);
    }

    private Hangout getExistingHangout(String hangoutId) {

        return hangoutRepository.findById(hangoutId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Hangout not found."
                ));
    }

    private void requireAdmin(Hangout hangout, String requestingUserId) {

        if (!hangout.getAdminId().equals(requestingUserId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Only the admin can perform this action."
            );
        }
    }

    private void updateTimestamp(Hangout hangout) {
        hangout.setUpdatedAt(LocalDateTime.now());
    }
}