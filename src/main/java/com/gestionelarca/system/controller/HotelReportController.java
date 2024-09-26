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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionelarca.system.DTO.HotelReportDTO;
import com.gestionelarca.system.model.HotelReport;
import com.gestionelarca.system.service.HotelReportService;

@RestController
@RequestMapping("/gestionElArca/v1/hotelReport")
public class HotelReportController {
    @Autowired
    private HotelReportService hotelReportService;

    @GetMapping()
    public ResponseEntity<?> listHotelReport() {
        Map<String, Object> res = new HashMap<>();
        try{
         return ResponseEntity.ok().body(hotelReportService.listHotelReport());
        }catch(DataAccessException err){
            res.put("message", "Error al momento de consultar a la base de datos. ");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
        }
        
    }

    @PostMapping()
    public ResponseEntity<?> addHotelReport(@RequestBody HotelReportDTO hotelReportDTO) {
        Map<String, Object> res = new HashMap<>();
        try {
            HotelReport hotelReport = hotelReportService.saveHotelReport(hotelReportDTO);
            res.put("message", "Se guardo exitosamente");
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @GetMapping("/{id}")
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
