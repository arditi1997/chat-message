package com.chat.message.service.impl;

import com.chat.message.exception.CustomException;
import com.chat.message.exception.ErrorCode;
import com.chat.message.model.ChatNotification;
import com.chat.message.model.User;
import com.chat.message.repository.NotificationRepository;
import com.chat.message.repository.UserRepository;
import com.chat.message.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void sendNotification(ChatNotification notification) {
        User userReceiver = userRepository.findByUserName(notification.getReceiver().getUserName()).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND.getMessage()));
        messagingTemplate.convertAndSend("topic/send-notification" + userReceiver.getUserName());
        notificationRepository.save(notification);
    }
}
