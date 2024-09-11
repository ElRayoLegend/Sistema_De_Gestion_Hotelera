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

import com.gestionelarca.system.model.Factura;
import com.gestionelarca.system.service.FacturaService;

@RestController
@RequestMapping("/gestionElArca/v1/auth/factura/")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @GetMapping()
    public List<Factura> listFactura() {
        return facturaService.listFactura();
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> addFactura(@RequestBody Factura factura) {
        Map<String, String> res = new HashMap<>();
        try {
            facturaService.saveFactura(factura);
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
    public ResponseEntity<?> findFactura(@PathVariable Long id) {
        Map<String, String> res = new HashMap<>();
        try {
            Factura factura = facturaService.findFactura(id);
            return ResponseEntity.ok(factura);

        } catch (Exception err) {
            System.out.println("Error, no se encontro el ID");
            res.put("message", "Se guardo exitosamente");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editFactura(@PathVariable Long id, @RequestBody Factura facturaReceived){
        Map<String, String> res = new HashMap<>();
        try {
            Factura factura = facturaService.findFactura(id);
            factura.setEmission(facturaReceived.getEmission());
            factura.setTotal(facturaReceived.getTotal());
            facturaService.saveFactura(factura);
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
    public ResponseEntity<Map<String, String>> deleteFactura(@PathVariable Long id){
        Map<String, String> res = new HashMap<>();
        try {
            Factura factura = facturaService.findFactura(id);
            facturaService.deleteFactura(factura);
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
