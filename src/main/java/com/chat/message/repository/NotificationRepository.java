package com.chat.message.repository;

import com.chat.message.model.ChatNotification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<ChatNotification, String> {
}
