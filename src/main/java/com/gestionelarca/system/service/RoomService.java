package com.gestionelarca.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.model.Room;
import com.gestionelarca.system.repository.RoomRepository;
import com.gestionelarca.system.service.IService.IRoomService;

@Service
public class RoomService implements IRoomService{

    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<Room> listRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoom(Long idRoom) {
        return roomRepository.findById(idRoom).orElse(null);
    }

    @Override
    public Room registerRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long idRoom){
        roomRepository.deleteById(idRoom);;
    }

    @Override
    public Room updateRoom(Long idRoom, Room updateRoom){
        if(roomRepository.existsById(idRoom)){
            updateRoom.setIdRoom(idRoom);
            return roomRepository.save(updateRoom);
        }else{
            return null;
        }
    }

}
