package com.gestionelarca.system.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.DTO.BillDTO;
import com.gestionelarca.system.DTO.BillResponseDTO;
import com.gestionelarca.system.DTO.BillSaveDTO;
import com.gestionelarca.system.model.Bill;
import com.gestionelarca.system.model.Reservation;
import com.gestionelarca.system.repository.BillRepository;
import com.gestionelarca.system.service.IService.IBillService;

@Service
public class BillService implements IBillService{
    @Autowired
    private BillRepository billRepository;

    @Autowired
    ReservationService reservationService;

    @Override
    public List<BillResponseDTO> listBill(Long id_Reservation) {
        Reservation reservation = reservationService.seachReservations(id_Reservation);
        List<Bill> bills = billRepository.findByReservation(reservation);

        return bills
            .stream()
            .map(bill -> responseDTO(bill))
            .collect(Collectors.toList());
    }

    @Override
    public Bill findBill(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public Bill saveBill(BillSaveDTO billSaveDTO) {
        try {
            
            Reservation reservation = reservationService.seachReservations(billSaveDTO.getId_Reservation());
            //Convertir la fecha que llega en STRING (LocalDateTime) a TIMESTAMP
            Timestamp emission = Timestamp.valueOf(billSaveDTO.getEmission());
            
            Bill bill = new Bill(
                null,
                emission,
                billSaveDTO.getTotal(),
                reservation
            );

            return billRepository.save(bill);
        } catch (Exception err) {
            throw new IllegalArgumentException("Ha ocurrido un error al guardar datos", err);
        }
    }

    @Override
    public Bill updateBill(Long id, BillDTO billDTO) {
        if(billRepository.existsById(id)) {
            Bill bill = findBill(id); // BUSCAR SERVICE RESERVATION

            // BUSCAR RESERVACION Y SERVICIO ADICIONAL
            Reservation reservation = reservationService.seachReservations(billDTO.getId_Reservation());
            //Convertir la fecha que llega en STRING (LocalDateTime) a TIMESTAMP
            Timestamp emission = Timestamp.valueOf(billDTO.getEmission());
            bill.setEmission(emission);
            bill.setTotal(billDTO.getTotal());
            bill.setReservation(reservation);

            return billRepository.save(bill);
        }else{
            return null;
        }
    }

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    private BillResponseDTO responseDTO(Bill bill){
        Reservation reservation = bill.getReservation();

        BillResponseDTO dto = new BillResponseDTO(
        bill.getId(),
        bill.getEmission(),
        bill.getTotal(),
        reservation
       );

       return dto;
   }
}
