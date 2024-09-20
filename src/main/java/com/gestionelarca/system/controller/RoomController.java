package com.gestionelarca.system.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionelarca.system.model.Room;
import com.gestionelarca.system.service.RoomService;

@RestController
@RequestMapping("/gestionElArca/v1/room")
public class RoomController{
    @Autowired
    RoomService roomService;

    @GetMapping()
    public ResponseEntity<?> getMethodName() {
        Map<String, Object> res = new HashMap<>();
        try{
         return ResponseEntity.ok().body(roomService.listRoom());
        }catch(DataAccessException err){
            res.put("message", "Error al momento de consultar a la base de datos. ");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
        }
        
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> registerRoom(@RequestBody Room room) {
        Map<String, String> res = new HashMap<>();
        try {
            roomService.registerRoom(room);
            res.put("message", "Habitacion guardada exitosamente");
            return ResponseEntity.ok(res);

        } catch (Exception err) {
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @GetMapping("/find/{idroom}")
    public ResponseEntity<?> getRoomlById(@PathVariable("idRoom") Long idRoom) {
        Map<String, Object> res = new HashMap<>();
        try {
            Room room = roomService.getRoom(idRoom);
            if (room != null) {
                res.put("room", room);
                return ResponseEntity.ok().body(res);
            } else {
                res.put("message", "Habitacion no encontrada");
                return ResponseEntity.status(404).body(res);
            }
        } catch (Exception e) {
            res.put("message", "Error al buscar la habitacion solicitada, intente de nuevo más tarde");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PutMapping("/edit/{idroom}")
    public ResponseEntity<?> updateRoom(@PathVariable("idRoom") Long idRoom, @RequestBody Room room) {
        Map<String, Object> res = new HashMap<>();
        try {
            Room updatedRoom = roomService.updateRoom(idRoom, room);
            if (updatedRoom != null) {
                res.put("message", "habitacion actualizada exitosamente.");
                res.put("event", updatedRoom);
                return ResponseEntity.ok().body(res);
            } else {
                res.put("message", "habitacion no encontrada.");
                return ResponseEntity.status(404).body(res);
            }
        } catch (Exception e) {
            res.put("message", "Error al actualizar la habitacion, intente de nuevo más tarde.");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @DeleteMapping("/delete/{idroom}")
    public ResponseEntity<?>deleteRoom(@PathVariable("idRoom")Long idRoom){
        Map<String, Object> res = new HashMap<>();
        try {
            Room room = roomService.getRoom(idRoom);
                if (room != null) {
                    roomService.deleteRoom(idRoom);
                    res.put("message", "Habitacion eliminada con exito.");
                    return ResponseEntity.ok().body(res);
                } else {
                    res.put("message", "Habitacion no encontrada.");
                    return ResponseEntity.status(404).body(res);
                }
        }  catch (Exception e) {
               res.put("message", "Error al eliminar la habitacion, intentelo mas tarde.");
               res.put("error", e.getMessage());
               return ResponseEntity.internalServerError().body(res);
        }

    }
    
    
}

