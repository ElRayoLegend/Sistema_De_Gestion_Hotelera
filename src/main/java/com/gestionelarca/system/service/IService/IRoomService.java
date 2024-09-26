package com.gestionelarca.system.service.IService;

import java.util.List;

import com.gestionelarca.system.DTO.RoomSaveDTO;
import com.gestionelarca.system.model.Room;

public interface IRoomService {
    
    List<Room> findRoomByHotel(Long idHotel);

    List<Room> myRooms();

    Room getRoom(Long idRoom);

    void deleteRoom(Long idRoom);

    Room updateRoom(Long id, Room updateRoom);

    Room save(RoomSaveDTO roomSaveDTO);
    
}