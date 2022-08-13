package com.chat.message.controller;

import com.chat.message.model.ChatMessage;
import com.chat.message.model.ChatNotification;
import com.chat.message.model.ChatRoom;
import com.chat.message.service.ChatMessageService;
import com.chat.message.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class MessageController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        messagingTemplate.convertAndSendToUser(
                username,"/queue/messages", chatMessage);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSenderId());
        return chatMessage;
    }
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }


//    @GetMapping("/rooms")
//    public ResponseEntity<List<ChatRoom>> getRooms(){
//        List<ChatRoom> chatRooms = chatRoomService.findAll();
//        return new ResponseEntity<>(chatRooms, HttpStatus.OK);
//    }
//
//    @PostMapping("/create-room")
//    public ResponseEntity<String> createRoom(@RequestBody ChatRoom chatRoom){
//            chatRoomService.save(chatRoom);
//        return new ResponseEntity<>("Chat room is created successfully!", HttpStatus.OK);
//    }
}
