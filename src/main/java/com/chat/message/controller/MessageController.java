package com.chat.message.controller;

import com.chat.message.model.Message;
import com.chat.message.model.Room;
import com.chat.message.service.MessageService;
import com.chat.message.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat.room-message/{to}")
    @SendTo("/topic/public")
    public void sendMessage(@DestinationVariable String to,@Payload Message message) {
        messageService.sendPublicAndSave(to, message);
    }

    @MessageMapping("/chat.private-message/{to}")
    public void sendPrivateMessage(@DestinationVariable String to, @Payload Message message) {
        messageService.sendPrivateAndSave(to, message);
    }

    @GetMapping("/messages/{from}/{to}")
    public void getMessages(@PathVariable("from") String from, @PathVariable("to") String to) {
        messageService.getMessages(from, to);
    }

    @GetMapping("/messages/{roomId}")
    public void getMessagesByRoom(@PathVariable("roomId") String roomId) {
        messageService.getMessagesByRoomId(roomId);
    }


}
