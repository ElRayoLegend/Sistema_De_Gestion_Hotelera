package com.gestionelarca.system.DTO;

import com.gestionelarca.system.model.AdditionalService;
import com.gestionelarca.system.model.Reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceReservationResponseDTO {
    private Long id;
    private Double subtotal;
    private Integer amount;
    private Reservation reservation;
    private AdditionalService additionalService;
}
