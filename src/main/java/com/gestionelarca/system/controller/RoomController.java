package com.gestionelarca.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;
import com.gestionelarca.system.DTO.RoomSaveDTO;
import com.gestionelarca.system.model.Event;
import com.gestionelarca.system.model.Room;
import com.gestionelarca.system.service.RoomService;
import com.gestionelarca.system.utils.StatusRoom;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gestionElArca/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping()
    public ResponseEntity<?> getMethodName(){
        Map<String, Object> res = new HashMap<>();
        try {

            return ResponseEntity.ok().body(roomService.myRooms());
            //Error conexion a la DB
        } catch (CannotCreateTransactionException err) {
            res.put("message", "Error al conectarse a la base de datos");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
            //Error de consulta a la DB
        } catch(DataAccessException err){
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

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody RoomSaveDTO roomSaveDTO) {
        Map<String, Object> res = new HashMap<>();
        try {
            Room room = roomService.save(roomSaveDTO);
            res.put("message", "Habitación reservada.");
            res.put("room", room);
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error al guardar la habitación, intente de nuevo más tarde.");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @GetMapping("/find/{idRoom}")
    public ResponseEntity<?> getRoomById(@PathVariable Long idRoom) {
        Map<String, Object> res = new HashMap<>();
        try {
            Room room = roomService.getRoom(idRoom);
            if (room != null) {
                res.put("room", room);
                return ResponseEntity.ok().body(res);
            } else {
                res.put("message", "Habitación no encontrada.");
                return ResponseEntity.status(404).body(res);
            }
        } catch (Exception e) {
            res.put("message", "Error al buscar la habitación solicitada.");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PutMapping("/edit/{idRoom}")
    public ResponseEntity<?> updateRoom(@PathVariable Long idRoom, @RequestBody Room room) {
        Map<String, Object> res = new HashMap<>();
        try {
            Room updatedRoom = roomService.updateRoom(idRoom, room);
            if (updatedRoom != null) {
                res.put("message", "Habitación actualizada exitosamente.");
                res.put("room", updatedRoom);
                return ResponseEntity.ok().body(res);
            } else {
                res.put("message", "Habitación no encontrada.");
                return ResponseEntity.status(404).body(res);
            }
        } catch (Exception e) {
            res.put("message", "Error al actualizar la habitación.");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @DeleteMapping("/delete/{idRoom}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long idRoom) {
        Map<String, Object> res = new HashMap<>();
        try {
            Room room = roomService.getRoom(idRoom);
            if (room != null) {
                roomService.deleteRoom(idRoom);
                res.put("message", "Habitación eliminada con éxito.");
                return ResponseEntity.ok().body(res);
            } else {
                res.put("message", "Habitación no encontrada.");
                return ResponseEntity.status(404).body(res);
            }
        } catch (Exception e) {
            res.put("message", "Error al eliminar la habitación.");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @GetMapping("/findHotelAvailable/{idHotel}")
    public ResponseEntity<?> getRoomByHotelAvailable(@PathVariable Long idHotel) {
        Map<String, Object> res = new HashMap<>();
        try {
            // Obtén la lista de habitaciones por hotel
            List<Room> rooms = roomService.findRoomByHotel(idHotel);
            
            // Filtrar las habitaciones que tienen el estado "AVAILABLE"
            List<Room> availableRooms = rooms.stream()
                    .filter(room -> StatusRoom.AVAILABLE.equals(room.getStatusRoom()))
                    .collect(Collectors.toList());
            
            if (availableRooms.isEmpty()) {
                res.put("message", "El hotel no tiene habitaciones disponibles");
                return ResponseEntity.status(404).body(res);
            } else {
                return ResponseEntity.ok(availableRooms);
            }
        } catch (Exception e) {
            res.put("message", "Error al buscar las habitaciones, intente de nuevo más tarde");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

}
