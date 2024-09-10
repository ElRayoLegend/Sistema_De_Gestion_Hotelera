package com.gestionelarca.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionelarca.system.model.Reservation;
import com.gestionelarca.system.service.IService.ReservationService;

@RestController
@RequestMapping("/gestionElArca/v1/auth/Reservation/")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping()
    public List<Reservation> listReservations(){
        return reservationService.listReservations();
    }


    @PostMapping()
    public ResponseEntity<Map<String, String>> addReservation(@RequestBody Reservation reservation){
        Map<String, String> res = new HashMap<>();
        try {
            reservationService.saveReservation(reservation);
            res.put("message", "Se guardo exitosamente");
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            System.out.println("Error, no se encontro el ID");
            res.put("message", "Se guardo exitosamente");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }


    @GetMapping("/find/{id_Reservation}")
    public ResponseEntity<?> seachReservations(@PathVariable Long id_Reservation){
         Map<String, String> res = new HashMap<>();
         try {
            Reservation reservation = reservationService.seachReservations(id_Reservation);
            return ResponseEntity.ok(reservation);
         } catch (Exception err) {
            System.out.println("Error, no se encontro el ID");
            res.put("message", "Se guardo exitosamente");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
         }
    }

    @PutMapping("/edit/{id_Reservation}")
    public ResponseEntity<?> editReservation(@PathVariable Long id_Reservation, @RequestBody Reservation reservationReceived){
        Map<String, String> res = new HashMap<>();
        try {
            Reservation reservation = reservationService.seachReservations(id_Reservation);
            reservation.setDate_start(reservationReceived.getDate_start());
            reservation.setDate_end(reservationReceived.getDate_end());
            reservation.setStatus(reservationReceived.getStatus());
            reservationService.saveReservation(reservation);
            return ResponseEntity.ok(reservation);
        } catch (Exception err) {
            System.out.println("Error, no se encontro el ID");
            res.put("message", "Se guardo exitosamente");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

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
