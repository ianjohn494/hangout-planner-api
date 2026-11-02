package com.hangoutplanner.service.impl;

import com.hangoutplanner.pojo.Hangout;
import com.hangoutplanner.pojo.Message;
import com.hangoutplanner.repository.HangoutRepository;
import com.hangoutplanner.repository.MessageRepository;
import com.hangoutplanner.service.MessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final HangoutRepository hangoutRepository;

    @Override
    public Message sendMessage(Message message, String senderId) {

        Hangout hangout = getExistingHangout(message.getHangoutId());

        boolean isMember = hangout.getMembers().stream()
                .anyMatch(member -> member.getUserId().equals(senderId));

        if (!isMember) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Only Hangout members can send messages."
            );
        }

        if (message.getContent() == null ||
                message.getContent().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Message content cannot be empty."
            );
        }

        message.setSenderId(senderId);
        message.setSentAt(LocalDateTime.now());
        message.setEdited(false);

        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesByHangoutId(
            String hangoutId,
            String requestingUserId) {

        Hangout hangout = getExistingHangout(hangoutId);

        requireMembership(hangout, requestingUserId);

        return messageRepository.findByHangoutIdOrderBySentAtAsc(hangoutId);
    }

    @Override
    public void deleteMessage(String messageId, String requestingUserId) {

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Message not found."
                ));

        Hangout hangout = getExistingHangout(message.getHangoutId());

        requireMembership(hangout, requestingUserId);

        boolean isAdmin = hangout.getAdminId().equals(requestingUserId);
        boolean isSender = message.getSenderId().equals(requestingUserId);

        if (!isAdmin && !isSender) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot delete this message."
            );
        }

        messageRepository.delete(message);

        log.info("Deleted message: {}", messageId);
    }

    private Hangout getExistingHangout(String hangoutId) {

        return hangoutRepository.findById(hangoutId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Hangout not found."
                ));
    }

    private void requireMembership(Hangout hangout, String userId) {

        boolean isMember = hangout.getMembers().stream()
                .anyMatch(member -> member.getUserId().equals(userId));

        if (!isMember) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You must be a member of this Hangout."
            );
        }
    }
}