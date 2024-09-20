package com.gestionelarca.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionelarca.system.DTO.BillDTO;
import com.gestionelarca.system.model.Bill;
import com.gestionelarca.system.model.Reservation;
;

public interface BillRepository extends JpaRepository<Bill, Long>{
    List<Bill> findByReservation(Reservation reservation);

    Bill save(BillDTO billDTO);
}
