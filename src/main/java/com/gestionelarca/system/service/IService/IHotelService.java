package com.gestionelarca.system.service.IService;

import java.util.List;

import com.gestionelarca.system.model.Hotel;

public interface IHotelService {
    List<Hotel> listHotel();

    Hotel getHotel(Long idHotel);

    Hotel register(Hotel hotel);

    void deleteHotel(Long idHotel);

    Hotel updateHotel(Long idHotel, Hotel updateHotel);
    
}
