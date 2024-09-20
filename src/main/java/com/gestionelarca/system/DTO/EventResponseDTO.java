package com.gestionelarca.system.DTO;

import java.sql.Timestamp;

import com.gestionelarca.system.model.Hotel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {
    private Long idEvents;
    private String eventname;
    private Timestamp start;
    private String eventtype;
    private Hotel idHotel;


}
