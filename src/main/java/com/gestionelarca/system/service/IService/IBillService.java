package com.gestionelarca.system.service.IService;

import java.util.List;

import com.gestionelarca.system.DTO.BillDTO;
import com.gestionelarca.system.DTO.BillResponseDTO;
import com.gestionelarca.system.DTO.BillSaveDTO;
import com.gestionelarca.system.model.Bill;

public interface IBillService {
    List<BillResponseDTO> listBill(Long id_Reservation);

    Bill findBill(Long id);

    Bill saveBill (BillSaveDTO billSaveDTO);

    Bill updateBill(Long id, BillDTO billDTO);

    void deleteBill(Long id);
}
