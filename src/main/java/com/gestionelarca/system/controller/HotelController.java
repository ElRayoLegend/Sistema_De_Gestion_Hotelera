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

import com.gestionelarca.system.model.Hotel;
import com.gestionelarca.system.service.HotelService;


@RestController
@RequestMapping("/gestionElArca/v1/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping()
    public ResponseEntity<?> getMethodName() {
        Map<String, Object> res = new HashMap<>();
        try{
         return ResponseEntity.ok().body(hotelService.listHotel());
        }catch(DataAccessException err){
            res.put("message", "Error al momento de consultar a la base de datos. ");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
        }
        
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> register(@RequestBody Hotel hotel) {
        Map<String, String> res = new HashMap<>();
        try {
            hotelService.register(hotel);
            res.put("message", "Se guardo exitosamente");
            return ResponseEntity.ok(res);

        } catch (Exception err) {
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @GetMapping("/find/{idHotel}")
    public ResponseEntity<?> getHotelById(@PathVariable("idHotel") Long idHotel) {
        Map<String, Object> res = new HashMap<>();
        try {
            Hotel hotel = hotelService.getHotel(idHotel);
            if (hotel != null) {
                res.put("event", hotel);
                return ResponseEntity.ok().body(res);
            } else {
                res.put("message", "Evento no encontrado");
                return ResponseEntity.status(404).body(res);
            }
        } catch (Exception e) {
            res.put("message", "Error al buscar el hotel solicitado, intente de nuevo más tarde");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }    

    @PutMapping("/edit/{idHotel}")
    public ResponseEntity<?> updateHotel(@PathVariable("idHotel") Long idHotel, @RequestBody Hotel hotel) {
        Map<String, Object> res = new HashMap<>();
        try {
            Hotel updatedHotel = hotelService.updateHotel(idHotel, hotel);
            if (updatedHotel != null) {
                res.put("message", "Hotel actualizado con exitosamente.");
                res.put("event", updatedHotel);
                return ResponseEntity.ok().body(res);
            } else {
                res.put("message", "Hotel no encontrado.");
                return ResponseEntity.status(404).body(res);
            }
        } catch (Exception e) {
            res.put("message", "Error al actualizar el hotel, intente de nuevo más tarde.");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @DeleteMapping("/delete/{idHotel}")
    public ResponseEntity<?>deleteHotel(@PathVariable("idHotel")Long idHotel){
        Map<String, Object> res = new HashMap<>();
        try {
            Hotel hotel = hotelService.getHotel(idHotel);
                if (hotel != null) {
                    hotelService.deleteHotel(idHotel);
                    res.put("message", "Hotel eliminado con exito.");
                    return ResponseEntity.ok().body(res);
                } else {
                    res.put("message", "Hotel no encontrado.");
                    return ResponseEntity.status(404).body(res);
                }
        }  catch (Exception e) {
               res.put("message", "Error al eliminar el hotel, intentelo mas tarde.");
               res.put("error", e.getMessage());
               return ResponseEntity.internalServerError().body(res);
        }

    }

    
}
