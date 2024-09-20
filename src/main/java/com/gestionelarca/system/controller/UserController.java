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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionelarca.system.DTO.UserLogin;
import com.gestionelarca.system.DTO.UserRegisterDTO;
import com.gestionelarca.system.model.User;
import com.gestionelarca.system.service.UserService;
import com.gestionelarca.system.utils.Rol;

import jakarta.validation.Valid;

// http://localhost:8085/

@RestController
@RequestMapping("/gestionElArca/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<?> getMethodName(){
        Map<String, Object> res = new HashMap<>();
        try {

            return ResponseEntity.ok().body(userService.listUsers());
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

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @Valid @ModelAttribute UserRegisterDTO user,
        BindingResult result
        ) {
        Map<String, Object> res = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.toList());
            res.put("message", "Error con las validaciones, por favor ingresa todos los campos");
            res.put("Errors", errors);
            return ResponseEntity.badRequest().body(res);
        }
        try {
            Long id = null;
            Rol rol = null;
            User newUser = new User(
                id,
                user.getPhoneNumber(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                rol
            );
            userService.register(newUser);
            res.put("message", "Usuario guardado correctamente");
            res.put("message", "Usuario recibido correctamente");
            return ResponseEntity.ok().body(res);
        } catch (Exception err) {
            res.put("message", "Error al guardar el usuario, intente de nuevo más tarde");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin user){
        Map<String, Object> res = new HashMap<>();
        try{
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
            if(userService.login(user.getUsername(), user.getPassword())){
                res.put("message", "Usuario logeado satisfactoriamente");
                return ResponseEntity.ok().body(res);
            }else{
                res.put("message", "Credenciales invalidas");
                return ResponseEntity.status(401).body(res);
            }
        } catch(Exception err){
            err.printStackTrace();
            res.put("message", "Error general al iniciar sesion");
            res.put("error", err);
            return ResponseEntity.internalServerError().body(res);
        }
    }
}
