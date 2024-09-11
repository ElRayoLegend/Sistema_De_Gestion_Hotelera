package com.gestionelarca.system.DTO;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomRegisterDTO {
    @NotBlank(message = "El tipo de habitacion no puede ir vacio")
    private String type;
    @NotNull(message = "La capacidad no puede ir vacia")
    private Integer capacity;
    @NotNull(message = "La fecha disponible no puede ir vacia")
    private Date avaible_date;
}
