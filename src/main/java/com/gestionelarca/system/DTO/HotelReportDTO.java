package com.gestionelarca.system.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class HotelReportDTO {
    @NotNull(message = "El total de reservaciones no puede ir vacio")
    private Long total_reservations;
    @NotNull(message = "El total de habitaciones ocupadas no puede ir vacio")
    private Long total_occupied_rooms;
    @NotNull(message = "El total de ingresos no puede ir vacio")
    private Double total_revenue;
    @NotNull(message = "La fecha no puede ir vacia")
    @PastOrPresent
    private LocalDateTime date;
    @NotNull(message = "No hay un hotel que analizar")
    private Long hotelId;
}
