package com.gestionelarca.system.DTO;

import java.sql.Timestamp;

import com.gestionelarca.system.model.Reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDTO {
    private Long id;
    private Timestamp emission;
    private Double total;
    private Reservation reservation;
}
