package com.chat.message.service;

import com.chat.message.model.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {

     void save(ChatRoom chatRoom);

     List<ChatRoom> findAll();

     Optional<ChatRoom> getChatRoomById(String id);
}
