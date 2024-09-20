package com.gestionelarca.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name = "id_Reservation")
    private Reservation reservation;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_Service")
    private AdditionalService additionalService;
    @NotNull
    private Integer amount;
    @NotNull
    private Double subtotal;
}
