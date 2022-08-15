package com.chat.message.repository;

import com.chat.message.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByFromAndTo(String form, String to);
    List<Message> findByRoomId(String rooId);
}
