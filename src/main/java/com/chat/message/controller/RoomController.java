package com.chat.message.controller;

import com.chat.message.model.Room;
import com.chat.message.model.User;
import com.chat.message.service.RoomService;
import com.chat.message.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/all-rooms")
    public ResponseEntity<?> getRooms(){
        return new ResponseEntity<>(roomService.getRooms(), HttpStatus.OK);
    }

    @MessageMapping("/chat.create-room")
    @SendTo("/topic/public")
    public void createRoom(@Payload Room room) {
        roomService.createRoom(room);
    }

    @MessageMapping("/chat.add-user-to-room")
    @SendTo("/topic/public")
    public void addUserToRoom(String roomId,  Principal principal) {
        roomService.addUserToRoom(roomId, principal);
    }

}
