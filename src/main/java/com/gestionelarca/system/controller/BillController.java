package com.gestionelarca.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionelarca.system.DTO.BillResponseDTO;
import com.gestionelarca.system.DTO.BillSaveDTO;
import com.gestionelarca.system.model.Bill;
import com.gestionelarca.system.service.BillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gestionElArca/v1/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping()
    public ResponseEntity<?> getBill(){
        Map<String, Object> res = new HashMap<>();
        try {

            return ResponseEntity.ok().body(billService.getBill());
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

    @GetMapping("/{id_Reservation}")
    public ResponseEntity<?> ListBill(@PathVariable Long id_Reservation) {
        Map<String, Object> res = new HashMap<>();
        try{
            List<BillResponseDTO> reservations = billService.listBill(id_Reservation);
            if(reservations == null || reservations.isEmpty()){
                res.put("message", "Aún no hay facturas creadas");
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
    public ResponseEntity<?> saveBill(
        @Valid @RequestBody BillSaveDTO billSaveDTO,
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
            Bill bill = billService.saveBill(billSaveDTO);
            res.put("message", "Factura guardada exitosamente");
            res.put("bill", bill);
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error al guardar la factura, intente de nuevo más tarde");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteBill(@PathVariable Long id){
        Map<String, String> res = new HashMap<>();
        try {
            billService.deleteBill(id);
            res.put("message", "Se elimino exitosamente");
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error, no se encontro el ID");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

}
