package com.gestionelarca.system.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceReservationSaveDTO {
    @NotNull(message="La cantidad no puede ir vacía")
    private Integer amount;
    @NotNull(message="El subtotal no puede ir vacía")
    private Double subtotal;
    @NotNull(message="El id de la habitacion no puede ir vacío")
    private Long id_room;
    @NotNull(message="El id del evento no puede ir vacío")
    private Long id_event;
    @NotNull(message="La id de servicio adicional no puede ir vacía")
    private Long id_Service;
}
