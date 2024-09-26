package com.gestionelarca.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.model.Hotel;
import com.gestionelarca.system.repository.HotelRepository;
import com.gestionelarca.system.repository.RoomRepository;
import com.gestionelarca.system.service.IService.IHotelService;

@Service
public class HotelService  implements IHotelService{
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<Hotel> listHotel() {
        return hotelRepository.findAll();
}

    @Override
    public Hotel getHotel(Long idHotel) {
        return hotelRepository.findById(idHotel).orElse(null);
    }

    @Override
    public Hotel register(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
    
    @Override
    public void deleteHotel(Long idHotel){
        hotelRepository.deleteById(idHotel);
    }

    @Override
    public Hotel updateHotel(Long idHotel, Hotel updateHotel){
        if (hotelRepository.existsById(idHotel)) {
            updateHotel.setIdHotel(idHotel);
            return hotelRepository.save(updateHotel);
        }else{
            return null;
        }
    }
    

}
