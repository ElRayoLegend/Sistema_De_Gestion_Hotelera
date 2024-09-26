package com.gestionelarca.system.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class EventsaveDTO {
        // Nombre del evento
   
    private String eventname;

    // Fecha de inicio del evento
    @FutureOrPresent(message = "La fecha de inicio debe ser hoy o en el futuro")
    private LocalDateTime start;

    // Tipo del evento
    private String eventtype;

    //llave de id hotel 
    @NotNull
    private Long idHotel;
    @NotNull
    private Long idUser;


}
