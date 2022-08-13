package com.chat.message.controller;


import com.chat.message.model.ChatMessage;
import com.chat.message.model.ChatRoom;
import com.chat.message.model.User;
import com.chat.message.service.ChatMessageService;
import com.chat.message.service.ChatRoomService;
import com.chat.message.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users =  users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createRoom(@RequestBody User user){
             userService.registerUser(user);
        return new ResponseEntity<>("User is created successfully!", HttpStatus.OK);
    }

    @RequestMapping({"/", "/home"})
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("indexHome");
        return modelAndView;
    }
}
