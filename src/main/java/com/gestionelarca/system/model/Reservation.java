package com.gestionelarca.system.model;

import java.sql.Timestamp;

import com.gestionelarca.system.utils.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Reservation;
    @NotNull
    @FutureOrPresent //Hace que la fecha para reservar no pueda ser pasado, sino que sea presente o futuro
    private Timestamp date_start;
    @NotNull
    @FutureOrPresent 
    private Timestamp date_end;
    @Enumerated(EnumType.STRING) //Para que tome el valor como un String
    private Status status;
    @NotNull
    @ManyToOne
    private User user;
    @NotNull
    @ManyToOne
    private Room room;
    @NotNull
    @ManyToOne
    private Event event;
}
