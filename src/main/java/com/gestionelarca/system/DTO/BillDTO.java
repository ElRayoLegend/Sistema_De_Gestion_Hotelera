package com.gestionelarca.system.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    @NotNull(message="La emisión no puede ir vacío")
    @FutureOrPresent
    private LocalDateTime emission;
    @NotNull(message="El total no puede ir vacío")
    private Double total;
    @NotNull(message="La id de reservación no puede ir vacío")
    private Long id_Reservation;
}