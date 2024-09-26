package com.gestionelarca.system.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.DTO.EventsaveDTO;
import com.gestionelarca.system.model.Event;
import com.gestionelarca.system.model.Hotel;
import com.gestionelarca.system.model.User;
import com.gestionelarca.system.repository.EventRepository;
import com.gestionelarca.system.service.IService.IEventService;


@Service

public class EventService   implements IEventService{
    @Autowired
    EventRepository eventRepository;
    @Autowired
    HotelService hotelService;
    @Autowired
    UserService userService;


    @Override
    public List<Event> listEvent() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEvent(long idEvents) {
        return eventRepository.findById(idEvents).orElse(null);
    }

    @Override
    public Event registerEvent(EventsaveDTO eventsaveDTO) {
        try {
            // Convertir LocalDateTime a Timestamp directamente
            Timestamp eventdate = Timestamp.valueOf(eventsaveDTO.getStart());
            
            // Obtenemos el Hotel vinculado al evento
            Hotel hotel = hotelService.getHotel(eventsaveDTO.getIdHotel());
            User user = userService.getUser(eventsaveDTO.getIdUser());
            
            Long idEvent = null;  // Esto lo manejaría la base de datos ya que usas IDENTITY
            Event event = new Event(
                idEvent,
                eventsaveDTO.getEventname(),
                eventdate,
                eventsaveDTO.getEventtype(),
                hotel,
                user
            );
            
            return eventRepository.save(event);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al procesar el evento", e);
        }

    }

    @Override
    public void deleteEvent(long idEvents) {
        eventRepository.deleteById(idEvents);
    }

    @Override
    public Event updateEvent(Long id, Event updatedEvent) {
        if (eventRepository.existsById(id)) {
            updatedEvent.setIdEvents(id); 
            return eventRepository.save(updatedEvent);
        } else {
            return null;
        }
    }

    // Metodo para listar por el id de hotel
    @Override
    public List<Event> myEvent(Long idHotel) {
        Hotel hotel =  hotelService.getHotel(idHotel);
        return eventRepository.findByIdHotel(hotel);
    }


    
}
