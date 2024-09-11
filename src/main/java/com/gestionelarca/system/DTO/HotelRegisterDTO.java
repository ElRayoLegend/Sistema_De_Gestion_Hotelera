package com.gestionelarca.system.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HotelRegisterDTO {
    //Es para mandar mensajes personalizados en los errores
    @NotBlank(message = "El nombre no puede ir vacio")//No es nulo y si trae un caracter vacio no lo va a aceptar
    private String name;
    @NotBlank(message = "La direccion no puede ir vacia") // No puede ser nulo y tampoco pude ser un caracter vacio
    private String address;
    @NotBlank(message = "La categoria no puede ir vacia") // No puede ser nulo y tampoco pude ser un caracter vacio
    private String category;
    @NotNull(message = "El precio no puede ir vacio")// No puede ser nulo y tampoco pude ser un caracter vacio
    private  Double room_price;
    @NotBlank(message = "Las comodidades no puede ir vaciaa")// No puede ser nulo y tampoco pude ser un caracter vacio
    private String amenities;

}
