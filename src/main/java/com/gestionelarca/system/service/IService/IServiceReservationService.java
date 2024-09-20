package com.gestionelarca.system.service.IService;

import java.util.List;

import com.gestionelarca.system.DTO.ServiceReservationDTO;
import com.gestionelarca.system.DTO.ServiceReservationResponseDTO;
import com.gestionelarca.system.DTO.ServiceReservationSaveDTO;
import com.gestionelarca.system.model.ServiceReservation;

public interface IServiceReservationService {
    List<ServiceReservationResponseDTO> listServiceReservation(Long id_Reservation);

    ServiceReservation findServiceReservation(Long id);

    ServiceReservation saveServiceReservation (ServiceReservationSaveDTO serviceReservationSaveDTO);
    
    ServiceReservation updateServiceReservation(Long id, ServiceReservationDTO serviceReservationDTO);
    
    void deleteServiceReservation(Long id);
}
