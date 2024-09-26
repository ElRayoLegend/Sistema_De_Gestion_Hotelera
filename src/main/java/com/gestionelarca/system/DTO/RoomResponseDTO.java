package com.gestionelarca.system.DTO;

import java.sql.Timestamp;

import com.gestionelarca.system.model.Hotel;
import com.gestionelarca.system.utils.StatusRoom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDTO {
    private Long idRoom;
    private String type;
    private Integer capacity;
    private StatusRoom StatusRoom;
    private Timestamp avaible_date;
    private Hotel hotel;

}
