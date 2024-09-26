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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionelarca.system.DTO.ServiceReservationDTO;
import com.gestionelarca.system.DTO.ServiceReservationResponseDTO;
import com.gestionelarca.system.DTO.ServiceReservationSaveDTO;
import com.gestionelarca.system.model.ServiceReservation;
import com.gestionelarca.system.service.ServiceReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gestionElArca/v1/serviceReservation")
public class ServiceReservationController {
    @Autowired
    private ServiceReservationService serviceReservationService;

    @GetMapping()
    public List<ServiceReservation> listAdditionalService() {
        return serviceReservationService.listRS();
    }

    @GetMapping("/ByAdditionalService/{id_Service}")
    public ResponseEntity<?> additionalReservationsByReservation(@PathVariable Long id_Service) {
        Map<String, Object> res = new HashMap<>();
        try{
            List<ServiceReservationResponseDTO> reservations = serviceReservationService.listByAdditionalService(id_Service);
            if(reservations == null || reservations.isEmpty()){
                res.put("message", "Aún no tienes asignaciones creadas");
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

    @PostMapping()
    public ResponseEntity<?> saveServiceReservation(
        @Valid @RequestBody ServiceReservationSaveDTO serviceReservationSaveDTO,
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
            ServiceReservation serviceReservation = serviceReservationService.saveServiceReservation(serviceReservationSaveDTO);
            res.put("message", "Asignación guardada exitosamente");
            res.put("serviceReservation", serviceReservation);
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error al guardar la asignación, intente de nuevo más tarde");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editService(
        @PathVariable Long id, 
        @Valid @RequestBody ServiceReservationDTO serviceReservationDTO,
        BindingResult result
        ){
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
            serviceReservationService.updateServiceReservation(id, serviceReservationDTO);
            res.put("message", "Se actualizo la informacion exitosamente");
            // res.put("serviceReservation", serviceReservation);
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error, no se encontro el ID");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteService(@PathVariable Long id){
        Map<String, String> res = new HashMap<>();
        try {
            serviceReservationService.deleteServiceReservation(id);
            res.put("message", "Se elimino exitosamente");
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error, no se encontro el ID");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }
}
