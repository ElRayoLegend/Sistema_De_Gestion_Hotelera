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

import com.gestionelarca.system.model.AdditionalService;
import com.gestionelarca.system.service.AdditionalServiceService;

@RestController
@RequestMapping("/gestionElArca/v1/additionalService")
public class AdditionalServiceController {
    @Autowired
    private AdditionalServiceService additionalServiceService;

    @GetMapping()
    public List<AdditionalService> listAdditionalService() {
        return additionalServiceService.listAdditionalService();
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> addServicio(@RequestBody AdditionalService additionalService) {
        Map<String, String> res = new HashMap<>();
        try {
            additionalServiceService.saveService(additionalService);
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
    public ResponseEntity<?> findService(@PathVariable Long id) {
        Map<String, String> res = new HashMap<>();
        try {
            AdditionalService additionalService = additionalServiceService.findService(id);
            return ResponseEntity.ok(additionalService);

        } catch (Exception err) {
            System.out.println("Error, no se encontro el ID");
            res.put("message", "Se guardo exitosamente");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editService(@PathVariable Long id, @RequestBody AdditionalService additionalServiceReceived){
        Map<String, String> res = new HashMap<>();
        try {
            AdditionalService additionalService = additionalServiceService.findService(id);
            additionalService.setName(additionalServiceReceived.getName());
            additionalService.setPrice(additionalServiceReceived.getPrice());
            additionalServiceService.saveService(additionalService);
            res.put("message", "Se actualizo la informacion exitosamente");
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            System.out.println("Error, no se encontro el ID");
            res.put("message", "Se guardo exitosamente");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteService(@PathVariable Long id){
        Map<String, String> res = new HashMap<>();
        try {
            AdditionalService additionalService = additionalServiceService.findService(id);
            additionalServiceService.deleteService(additionalService);
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
