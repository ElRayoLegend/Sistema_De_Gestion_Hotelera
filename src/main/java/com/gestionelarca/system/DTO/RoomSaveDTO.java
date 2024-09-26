package com.gestionelarca.system.DTO;

import java.time.LocalDateTime;

import com.gestionelarca.system.utils.StatusRoom;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

@Data
public class RoomSaveDTO {
    private String type;

    private Long capacity;

    @FutureOrPresent
    private LocalDateTime avaible_date;

    @Enumerated(EnumType.STRING)
    private StatusRoom statusRoom;
    private Long idhotel;

}
