package com.gestionelarca.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionelarca.system.model.Event;
import com.gestionelarca.system.model.Hotel;
@Repository
public interface EventRepository extends  JpaRepository<Event, Long>{
    // metodo paa que busque todos los  eventos de un Hotel  
    List<Event> findByIdHotel(Hotel hotel);
}
