package com.gestionelarca.system.service.IService;
import java.util.List;

import com.gestionelarca.system.DTO.EventsaveDTO;
import com.gestionelarca.system.model.Event;
public interface IEventService {
    //listar
    List<Event> listEvent();
    //Buscar
    Event getEvent(long idEvents);
    //GUardar
    Event registerEvent(EventsaveDTO eventsaveDTO);
    //Eliminar
    void deleteEvent(long idEvents);
    //Actualizar
    Event updateEvent(Long id, Event updatedEvent);
    // metodo para listar los eventos segun
    List<Event> myEvent(Long idHotel);
}
