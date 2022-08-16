package com.chat.message.controller;

import com.chat.message.model.ChatNotification;
import com.chat.message.model.Message;
import com.chat.message.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @MessageMapping("/chat.room-message/{to}")
    @SendTo("/topic/public")
    public void sendMessage(@Payload ChatNotification notification) {
        notificationService.sendNotification(notification);
    }
}
