package com.gestionelarca.system.service.IService;

import java.sql.Timestamp;
import java.util.List;

import com.gestionelarca.system.DTO.ReservationResponseDTO;
import com.gestionelarca.system.DTO.ReservationSaveDTO;
import com.gestionelarca.system.model.Reservation;

public interface IReservationService {

    public List<Reservation> listReservations();
    public Reservation  seachReservations(Long id_Reservation);
    public void deleteReservation(Reservation reservation);

    //Método para listar reservaciones (SOLO DE UN USUARIO EN ESPECÍFICO)
    List<ReservationResponseDTO> myReservations(Long userId);
    
    //Método para mostrar solo 1 reservación por su Id
    Reservation findByIdReservation(Long id);

    Reservation save(ReservationSaveDTO reservationDTO);

    List myReservation(Long userId);

    List myHotel(Long hotelId);
}
