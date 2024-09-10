package com.gestionelarca.system.service.IService;

import java.util.List;

import com.gestionelarca.system.model.Reservation;

public interface IReservationService {

    public List<Reservation> listReservations();
    public Reservation  seachReservations(Long id_Reservation);
    public Reservation saveReservation(Reservation reservation);
    public void deleteReservation(Reservation reservation);

}
