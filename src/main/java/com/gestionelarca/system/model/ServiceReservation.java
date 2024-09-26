package com.gestionelarca.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ServiceReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServiceReservation;
    @NotNull
    @ManyToOne
    private Room room;
    @NotNull
    @ManyToOne
    private Event event;
    @NotNull
    @ManyToOne
    private AdditionalService additionalService;
    @NotNull
    private Integer amount;
    @NotNull
    private Double subtotal;
}
