package com.chat.message.service.impl;

import com.chat.message.model.ChatMessage;
import com.chat.message.repository.ChatMessageRepository;
import com.chat.message.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public List<ChatMessage> findByChatId(String chatId) {
        return chatMessageRepository.findByChatId(chatId);
    }
}
