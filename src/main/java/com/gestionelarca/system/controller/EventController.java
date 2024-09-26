package com.gestionelarca.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionelarca.system.DTO.EventsaveDTO;
import com.gestionelarca.system.model.Event;
import com.gestionelarca.system.service.EventService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/gestionElArca/v1/event")
public class EventController {
    @Autowired
    EventService eventService;
    

    @GetMapping()
    public ResponseEntity<?> getMethodoName(){
        Map<String, Object> res = new HashMap<>();
        try {
              
            return ResponseEntity.ok().body(eventService.listEvent());
        } catch (CannotCreateTransactionException err) {
            res.put("message", "Error al conectarse a la base de datos");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
            //Error de consulta a la DB
        }catch(DataAccessException err){
            res.put("message", "Error al momento de consultar a la base de datos");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
            //Error general o generico
        } catch(Exception err){
            res.put("message", "Error general al obtener los datos");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }



// Agregar
    @PostMapping()

    public ResponseEntity <?> rejister ( @Valid @RequestBody EventsaveDTO eventsaveDTO){
        Map<String, Object> res = new  HashMap<>();
        try {
            Event event = eventService.registerEvent(eventsaveDTO);
            res.put("message", "Evento Agregado exitosamente");
            res.put("event", event);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("message", "Error al guardar el evento, intente de nuevo más tarde");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }

    }




//eliminar 
    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
        Map<String, Object> res = new HashMap<>();
        try {
            Event event = eventService.getEvent(id);
            if (event != null) {
                eventService.deleteEvent(id);
                res.put("message", "Evento eliminado exitosamente");
                return ResponseEntity.ok().body(res);
            } else {
                res.put("message", "Evento no encontrado");
                return ResponseEntity.status(404).body(res);
            }
        } catch (Exception e) {
            res.put("message", "Error al eliminar el evento, intente de nuevo más tarde");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }


// editar pero le tenes que pasar todos los datos 
    @PutMapping("/editEvent/{idEvent}")
    public ResponseEntity<?> updateEvent(@PathVariable("idEvent") Long id, @RequestBody Event event) {
        Map<String, Object> res = new HashMap<>();
        try {
            Event updatedEvent = eventService.updateEvent(id, event);
            if (updatedEvent != null) {
                res.put("message", "Evento actualisado exitosamente");
                res.put("event", updatedEvent);
                return ResponseEntity.ok().body(res);
            } else {
                res.put("message", "Evento no encontrado");
                return ResponseEntity.status(404).body(res);
            }
        } catch (Exception e) {
            res.put("message", "Error al actualizar el evento, intente de nuevo más tarde");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }
    


//Buscar evento por id de hotel
    @GetMapping("/findEvent/{idHotel}")
    public ResponseEntity<?> getEventById(@PathVariable Long idHotel) {
        Map<String, Object> res = new HashMap<>();
        try {
      
            List<Event> event = eventService.myEvent(idHotel);
            if (event == null || event.isEmpty()) {
                res.put("message", "El hotel no tiene eventos");
                return ResponseEntity.status(404).body(res);
            } else {
                res.put("message", "Evento no encontrado");
                return ResponseEntity.ok(event);
            }
        } catch (Exception e) {
            res.put("message", "Error al buscar el evento, intente de nuevo más tarde");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }
    
}
