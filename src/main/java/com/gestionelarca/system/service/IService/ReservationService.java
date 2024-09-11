package com.gestionelarca.system.service.IService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.model.Reservation;
import com.gestionelarca.system.repository.ReservationRepository;

@Service
public class ReservationService implements IReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> listReservations(){
        return reservationRepository.findAll();
    }

    @Override
    public Reservation seachReservations(Long id_Reservation){
        Reservation reservation = reservationRepository.findById(id_Reservation).orElse(null);
        return reservation;
    }

    @Override
    public Reservation saveReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Reservation reservation){
        reservationRepository.delete(reservation);
    }
    
}
