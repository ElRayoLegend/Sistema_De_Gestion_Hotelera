package com.gestionelarca.system.DTO;

import com.gestionelarca.system.model.AdditionalService;
import com.gestionelarca.system.model.Event;
import com.gestionelarca.system.model.Room;

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
    private Room room;
    private Event event;
    private AdditionalService additionalService;
}
