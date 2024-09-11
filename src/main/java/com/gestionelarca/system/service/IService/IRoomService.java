package com.gestionelarca.system.service.IService;

import java.util.List;

import com.gestionelarca.system.model.Room;

public interface IRoomService {
    List<Room> listRoom();

    Room getRoom(Long idRoom);

    Room registerRoom(Room room);

    void deleteRoom(Long idRoom);

    Room updateRoom(Long idRoom, Room updateRoom);

}
