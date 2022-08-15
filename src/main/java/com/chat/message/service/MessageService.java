package com.chat.message.service;

import com.chat.message.model.Message;

import java.util.List;

public interface MessageService {
    void sendPrivateAndSave(String to, Message message);
    List<Message> getMessages(String from, String to);
    List<Message> getMessagesByRoomId(String roomId);
    void sendPublicAndSave(String to, Message message);

}
