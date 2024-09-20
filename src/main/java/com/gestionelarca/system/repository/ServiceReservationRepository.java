package com.gestionelarca.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionelarca.system.DTO.ServiceReservationDTO;
import com.gestionelarca.system.model.Reservation;
import com.gestionelarca.system.model.ServiceReservation;

public interface ServiceReservationRepository extends JpaRepository<ServiceReservation, Long>{
    List<ServiceReservation> findByReservation(Reservation reservation);

    ServiceReservation save(ServiceReservationDTO serviceReservationDTO);
}
