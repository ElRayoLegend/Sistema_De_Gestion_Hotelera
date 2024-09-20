package com.gestionelarca.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionelarca.system.DTO.ReservationResponseDTO;
import com.gestionelarca.system.DTO.ReservationSaveDTO;
import com.gestionelarca.system.model.Reservation;
import com.gestionelarca.system.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gestionElArca/v1/Reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping()
    public ResponseEntity<?> listReservations(){
        Map<String, Object> res = new HashMap<>();
        List<Reservation> reservations = reservationService.listReservations();
        if(reservations == null || reservations.isEmpty()){
            res.put("message", "Aún no tienes reservaciones creadas");
            return ResponseEntity.status(404).body(res);
        }else{
            return ResponseEntity.ok(reservations);
        }
    }

    //Metodo actualizado
    //Metodo de guardar(agregar)

    @PostMapping()
    public ResponseEntity<?> saveReservation(
        @Valid @RequestBody ReservationSaveDTO reservationDTO,
        BindingResult result
    ) {
        Map<String, Object> res = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
                res.put("Errors", errors);
                return ResponseEntity.badRequest().body(res);
        }
        try {
            Reservation reservation = reservationService.save(reservationDTO);
            res.put("message", "Reservación guardada exitosamente");
            res.put("reservation", reservation);
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error al guardar la reservacion, intente de nuevo más tarde");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    //Metodo actualizado
    //Metodo de buscar
    @GetMapping("/{id_Reservation}")
    public ResponseEntity<?> myReservations(@PathVariable Long id_Reservation) {
        Map<String, Object> res = new HashMap<>();
        try{
            List<ReservationResponseDTO> reservations = reservationService.myReservations(id_Reservation);
            if(reservations == null || reservations.isEmpty()){
                res.put("message", "Aún no tienes reservaciones creadas");
                return ResponseEntity.status(404).body(res);
            }else{
                return ResponseEntity.ok(reservations);
            }
        }catch(Exception err){
            res.put("message", "Error general al obtener los datos");
            res.put("error", err);
            return ResponseEntity.internalServerError().body(res);
        }
    }

    //Metodo de eliminar
    @DeleteMapping("/delete/{id_Reservation}")
    public ResponseEntity<Map<String, String>> deleteReservation(@PathVariable Long id_Reservation){
        Map<String, String> res = new HashMap<>();
        try {
            Reservation reservation = reservationService.seachReservations(id_Reservation);
            reservationService.deleteReservation(reservation);
            res.put("message", "Se elimino exitosamente");
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            System.out.println("Error, no se encontro el ID");
            res.put("message", "Se guardo exitosamente");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }

    }
}
