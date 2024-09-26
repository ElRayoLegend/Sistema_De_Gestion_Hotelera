package com.gestionelarca.system.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestionelarca.system.model.Hotel;
import com.gestionelarca.system.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotel(Hotel hotel); // Cambiado a buscar por la propiedad hotel
}
