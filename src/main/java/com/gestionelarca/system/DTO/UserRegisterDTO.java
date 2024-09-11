package com.gestionelarca.system.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotBlank(message = "El numero de telefono no puede ir vacio")
    private String phoneNumber;
    @NotBlank(message = "El nombre no puede ir vacio")
    private String name;
    @NotBlank(message = "El apellido no puede ir vacio")
    private String surname;
    @NotBlank(message = "El username no puede ir vacio")
    private String username;
    @NotBlank(message = "El email no puede ir vacio")
    @Email
    private String email;
    @NotBlank(message = "La password no puede ir vacia")
    private String password;
}
