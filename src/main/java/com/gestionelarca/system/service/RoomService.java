package com.gestionelarca.system.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.DTO.RoomSaveDTO;
import com.gestionelarca.system.model.Hotel;
import com.gestionelarca.system.model.Room;
import com.gestionelarca.system.repository.RoomRepository;
import com.gestionelarca.system.service.IService.IRoomService;

@Service
public class RoomService implements IRoomService{

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelService hotelService;

    @Override
    public List<Room> findRoomByHotel(Long idHotel){
        Hotel hotel = hotelService.getHotel(idHotel);
        return roomRepository.findByHotel(hotel);
    }

    @Override
    public List<Room> myRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoom(Long idRoom) {
        return roomRepository.findById(idRoom).orElse(null);
    }

    @Override
    public void deleteRoom(Long idRoom){
        roomRepository.deleteById(idRoom);
    }

    @Override
    public Room updateRoom(Long id, Room updateRoom){
        if(roomRepository.existsById(id)){
            updateRoom.setIdRoom(id);
            return roomRepository.save(updateRoom);
        }else{
            return null;
        }
    }



 
    // GUARDAR 
    @Override
    public Room save(RoomSaveDTO roomSaveDTO) {
            try {
                    // parceamos las fechas
                Timestamp avaible = Timestamp.valueOf(roomSaveDTO.getAvaible_date());
                // obtenemos la entidad hotel
                Hotel hotel = hotelService.getHotel(roomSaveDTO.getIdhotel());
                Long idRoom = null;
                Room room = new Room(
                    idRoom,
                    roomSaveDTO.getType(),
                    roomSaveDTO.getCapacity(),
                    avaible,
                    roomSaveDTO.getStatusRoom(),
                    hotel

                );
                 return roomRepository.save(room);
            } catch (Exception err) {
                throw new IllegalArgumentException("Error al guardar la habitacion", err);
            }

        
    }

    


}
