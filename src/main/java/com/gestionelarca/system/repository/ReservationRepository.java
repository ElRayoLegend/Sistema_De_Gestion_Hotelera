package com.gestionelarca.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionelarca.system.model.Reservation;
import com.gestionelarca.system.model.Room;
import com.gestionelarca.system.model.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    //Metodo personalizado que busque y devuelva todas las reservas de un solo usuario
    List<Reservation> findByUser(User user);
    //Metodo personmalizado para que busque y devuelva todas las reservas de una sola habitacion
    List<Reservation> findByRoom(Room room);
    //Metodo personalizado para que busque y devuelva todas las reservas de un solo evento
    // List<Reservation> findByEvent(Event event);

}
