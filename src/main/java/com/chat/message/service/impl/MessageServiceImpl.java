package com.chat.message.service.impl;

import com.chat.message.exception.CustomException;
import com.chat.message.exception.ErrorCode;
import com.chat.message.model.Message;
import com.chat.message.model.User;
import com.chat.message.repository.MessageRepository;
import com.chat.message.repository.RoomRepository;
import com.chat.message.repository.UserRepository;
import com.chat.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void sendPrivateAndSave(String to, Message message) {
        User user = userRepository.findByUserName(to).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND.getMessage()));
        messagingTemplate.convertAndSend("topic/messages/private" + user.getUserName(), message);
        message.setTo(user);
        messageRepository.save(message);
    }

    @Override
    public List<Message> getMessages(String from, String to) {
        validateUsers(from,to);
        return messageRepository.findByFromAndTo(from, to);
    }

    @Override
    public List<Message> getMessagesByRoomId(String roomId) {
        return messageRepository.findByRoomId(roomId);
    }

    @Override
    public void sendPublicAndSave(String to, Message message) {
        User user = userRepository.findByUserName(to).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND.getMessage()));
        messagingTemplate.convertAndSend("topic/messages/public" + user.getUserName(), message);
        message.setTo(user);
        messageRepository.save(message);
    }

    void validateUsers(String from, String to){
        Optional<User> userFrom = userRepository.findByUserName(from);
        Optional<User> userTo = userRepository.findByUserName(to);
         if(userFrom.isEmpty() || userTo.isEmpty()){
             throw new CustomException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND.getMessage());
         }
    }
}
