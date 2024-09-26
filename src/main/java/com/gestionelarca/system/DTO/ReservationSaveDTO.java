package com.gestionelarca.system.DTO;

import java.time.LocalDateTime;

import com.gestionelarca.system.utils.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationSaveDTO {
    @NotNull(message = "La fecha de inicio no puede ir vacia")
    @FutureOrPresent //Hace que la fecha para reservar no pueda ser pasado, sino que sea presente o futuro
    private LocalDateTime date_start;
    @NotNull(message = "La fecha de finalizacion no puede ir vacia")
    @FutureOrPresent 
    private LocalDateTime date_end;
    @Enumerated(EnumType.STRING) //Para que tome el valor como un String
    private Status status;
    @NotNull(message = "No hay un usuario para reservar")
    private Long userId;
    @NotNull(message = "No hay habitacion para reservar")
    private Long roomId;
    
}
