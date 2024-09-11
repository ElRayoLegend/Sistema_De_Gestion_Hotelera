package com.gestionelarca.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionelarca.system.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
