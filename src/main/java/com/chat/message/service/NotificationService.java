package com.chat.message.service;

import com.chat.message.model.ChatNotification;
import com.chat.message.model.Message;

public interface NotificationService {
    void sendNotification(ChatNotification notification);
}
