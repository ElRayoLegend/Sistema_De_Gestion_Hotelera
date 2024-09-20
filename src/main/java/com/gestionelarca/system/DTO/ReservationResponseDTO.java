package com.gestionelarca.system.DTO;

import java.sql.Timestamp;

import com.gestionelarca.system.model.Event;
import com.gestionelarca.system.model.Room;
import com.gestionelarca.system.utils.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {
    private long id_Reservation;
    private Timestamp date_start;
    private Timestamp date_end;
    private Status status;
    private UserClearDTO user;
    private Room room;
    private Event event;

}
