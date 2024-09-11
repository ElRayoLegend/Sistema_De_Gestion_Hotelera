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

import com.gestionelarca.system.model.HotelReport;
import com.gestionelarca.system.service.HotelReportService;

@RestController
@RequestMapping("/gestionElArca/v1/auth/hotelReport/")
public class HotelReportController {
    @Autowired
    private HotelReportService hotelReportService;

    @GetMapping()
    public List<HotelReport> listHotelReport() {
        return hotelReportService.listHotelReport();
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> addHotelReport(@RequestBody HotelReport hotelReport) {
        Map<String, String> res = new HashMap<>();
        try {
            hotelReportService.saveHotelReport(hotelReport);
            res.put("message", "Se guardo exitosamente");
            return ResponseEntity.ok(res);

        } catch (Exception err) {
            System.out.println("Error, no se encontro el ID");
            res.put("message", "Se guardo exitosamente");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findHotelReport(@PathVariable Long id) {
        Map<String, String> res = new HashMap<>();
        try {
            HotelReport hotelReport = hotelReportService.findHotelReport(id);
            return ResponseEntity.ok(hotelReport);

        } catch (Exception err) {
            System.out.println("Error, no se encontro el ID");
            res.put("message", "Se guardo exitosamente");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editHotelReport(@PathVariable Long id, @RequestBody HotelReport hotelReportReceived){
        Map<String, Object> res = new HashMap<>();
        try {
            HotelReport hotelReport = hotelReportService.findHotelReport(id);
            hotelReport.setTotal_reservations(hotelReportReceived.getTotal_reservations());
            hotelReport.setTotal_occupied_rooms(hotelReportReceived.getTotal_occupied_rooms());
            hotelReport.setTotal_revenue(hotelReportReceived.getTotal_revenue());
            hotelReport.setDate(hotelReportReceived.getDate());
            hotelReportService.saveHotelReport(hotelReport);
            res.put("message", "Se actualizo la informacion exitosamente");
            res.put("event", hotelReport);
            return ResponseEntity.ok().body(res);
        } catch (Exception err) {
            System.out.println("Error, no se encontro el ID");
            res.put("message", "Se guardo exitosamente");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteHotelReport(@PathVariable Long id){
        Map<String, String> res = new HashMap<>();
        try {
            HotelReport hotelReport = hotelReportService.findHotelReport(id);
            hotelReportService.deleteHotelReport(hotelReport);
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
