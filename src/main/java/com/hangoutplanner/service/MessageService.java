package com.hangoutplanner.service;

import com.hangoutplanner.pojo.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Message message, String senderId);

    List<Message> getMessagesByHangoutId(String hangoutId, String requestingUserId);

    void deleteMessage(String messageId, String requestingUserId);
}