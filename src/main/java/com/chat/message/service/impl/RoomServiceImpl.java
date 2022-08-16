package com.chat.message.service.impl;

import com.chat.message.exception.CustomException;
import com.chat.message.exception.ErrorCode;
import com.chat.message.model.Role;
import com.chat.message.model.Room;
import com.chat.message.model.User;
import com.chat.message.repository.RoleRepository;
import com.chat.message.repository.RoomRepository;
import com.chat.message.repository.UserRepository;
import com.chat.message.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void addUserToRoom(String roomId, Principal principal) {
        User userFound = userRepository.findByUserName(principal.getName()).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND.getMessage()));
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new CustomException(ErrorCode.ROOM_NOT_FOUND, ErrorCode.ROOM_NOT_FOUND.getMessage()));
        room.getUsersList().add(userFound);
        roomRepository.save(room);
    }

    @Override
    public void createRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public List<Room> getRooms() {
       return roomRepository.findAll();
    }

}
