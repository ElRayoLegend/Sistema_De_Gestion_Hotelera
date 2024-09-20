package com.gestionelarca.system.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    // ID del evento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvents;

    // Nombre del evento
    @NotBlank(message = "El nombre del evento no debe estar vacío")
    private String eventname;

    // Fecha de inicio del evento
    @FutureOrPresent(message = "La fecha de inicio debe ser hoy o en el futuro")
    private Timestamp start;

    // Tipo del evento
    @NotBlank(message = "El tipo del evento no debe estar vacío")
    private String eventtype;
    @NotNull
    @ManyToOne
    private Hotel idHotel;

}
