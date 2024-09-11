package com.gestionelarca.system.IService;
import java.util.List;

import com.gestionelarca.system.model.Event;
public interface IEventService {
    List<Event> listEvent();
    Event getEvent(long idEvents);
    Event registerEvent(Event event);
    void deleteEvent(long idEvents);
    Event updateEvent(Long id, Event updatedEvent);
}
