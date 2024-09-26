package com.gestionelarca.system.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class HotelReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long total_reservations;
    @NotNull
    private Long total_occupied_rooms;
    @NotNull
    private Double total_revenue;
    @NotNull
    @PastOrPresent
    private Timestamp date;
    @NotNull
    @ManyToOne
    private Hotel hotel;

}
