package com.gestionelarca.system.model;

import java.io.ObjectInputFilter.Status;
import java.sql.Date;

import com.gestionelarca.system.utils.StatusRoom;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoom;
    @NotBlank
    private String type;
    @NotNull
    private Integer capacity;
    @NotNull
    @FutureOrPresent
    private Date avaible_date;
    @Enumerated(EnumType.STRING)
    private StatusRoom StatusRoom;

}
