package com.gestionelarca.system.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLogin {
    @NotBlank(message = "El username no puede ir vacio")
    private String username;
    
    @NotBlank(message = "La password no puede ir vacia")
    private String password;
}
