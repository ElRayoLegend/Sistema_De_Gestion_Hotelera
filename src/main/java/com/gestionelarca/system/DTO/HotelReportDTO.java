package com.gestionelarca.system.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HotelReportDTO {
    @NotNull(message = "El total de reservaciones no puede ir vacio")
    private Long total_reservations;
    @NotNull(message = "El total de habitaciones ocupadas no puede ir vacio")
    private Long total_occupied_rooms;
    @NotNull(message = "El total de ingresos no puede ir vacio")
    private Double total_revenue;
}
