package com.gestionelarca.system.model;

import java.util.Date;

import com.gestionelarca.system.utils.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Reservation;
    @NotNull
    @FutureOrPresent //Hace que la fecha para reservar no pueda ser pasado, sino que sea presente o futuro
    private Date date_start;
    @NotNull
    @FutureOrPresent 
    private Date date_end;
    @Enumerated(EnumType.STRING) //Para que tome el valor como un String
    private Status status;
}
