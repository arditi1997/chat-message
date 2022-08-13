package com.chat.message.service;

import com.chat.message.model.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    List<ChatMessage> findByChatId(String chatId);
}
