package com.chat.message.service;

import com.chat.message.model.Role;
import com.chat.message.model.Room;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface RoomService {
     void addUserToRoom(String roomId, Principal principal);
     void createRoom(Room room);
     List<Room> getRooms();
}
