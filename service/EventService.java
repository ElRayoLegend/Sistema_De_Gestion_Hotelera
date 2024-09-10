package com.gestionelarca.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.IService.IEventService;
import com.gestionelarca.system.model.Event;
import com.gestionelarca.system.repository.EventRepository;
@Service

public class EventService   implements IEventService{
    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> listEvent() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEvent(long idEvents) {
        return eventRepository.findById(idEvents).orElse(null);
    }

    @Override
    public Event registerEvent(Event event) {
        return eventRepository.save(event);

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

    

}
