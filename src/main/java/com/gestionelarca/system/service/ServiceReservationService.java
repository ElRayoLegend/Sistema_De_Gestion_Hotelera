package com.gestionelarca.system.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.DTO.ServiceReservationDTO;
import com.gestionelarca.system.DTO.ServiceReservationResponseDTO;
import com.gestionelarca.system.DTO.ServiceReservationSaveDTO;
import com.gestionelarca.system.model.AdditionalService;
import com.gestionelarca.system.model.Reservation;
import com.gestionelarca.system.model.ServiceReservation;
import com.gestionelarca.system.repository.ServiceReservationRepository;
import com.gestionelarca.system.service.IService.IServiceReservationService;

@Service
public class ServiceReservationService implements IServiceReservationService {

    @Autowired
    ServiceReservationRepository serviceReservationRepository;

    @Autowired
    ReservationService reservationService;

    @Autowired
    AdditionalServiceService additionalServiceService;

    @Override
    public List<ServiceReservationResponseDTO> listServiceReservation(Long id_Reservation) {
        Reservation reservation = reservationService.seachReservations(id_Reservation);
        List<ServiceReservation> lServiceReservations = serviceReservationRepository.findByReservation(reservation);

        return lServiceReservations
            .stream()
            .map(serviceReservation -> responseDTO(serviceReservation))
            .collect(Collectors.toList());
    }

    @Override
    public ServiceReservation findServiceReservation(Long id) {
        return serviceReservationRepository.findById(id).orElse(null);
    }

    @Override
    public ServiceReservation saveServiceReservation(ServiceReservationSaveDTO serviceReservationSaveDTO) {
        try {
            
            Reservation reservation = reservationService.seachReservations(serviceReservationSaveDTO.getId_Reservation());
            AdditionalService additionalService = additionalServiceService.findService(serviceReservationSaveDTO.getId_Service());
            
            ServiceReservation serviceReservation = new ServiceReservation(
                null,
                reservation,
                additionalService,
                serviceReservationSaveDTO.getAmount(),
                serviceReservationSaveDTO.getSubtotal()
            );

            return serviceReservationRepository.save(serviceReservation);
        } catch (Exception err) {
            throw new IllegalArgumentException("Ha ocurrido un error al guardar datos", err);
        }
    }

    @Override
    public ServiceReservation updateServiceReservation(Long id, ServiceReservationDTO serviceReservationDTO) {
        if(serviceReservationRepository.existsById(id)) {
            ServiceReservation serviceReservation = findServiceReservation(id); // BUSCAR SERVICE RESERVATION

            // BUSCAR RESERVACION Y SERVICIO ADICIONAL
            Reservation reservation = reservationService.seachReservations(serviceReservationDTO.getId_Reservation());
            AdditionalService additionalService = additionalServiceService.findService(serviceReservationDTO.getId_Service());

            serviceReservation.setAmount(serviceReservationDTO.getAmount());
            serviceReservation.setSubtotal(serviceReservationDTO.getSubtotal());
            serviceReservation.setReservation(reservation);
            serviceReservation.setAdditionalService(additionalService);

            return serviceReservationRepository.save(serviceReservation);
        }else{
            return null;
        }
    }

    @Override
    public void deleteServiceReservation(Long id) {
        serviceReservationRepository.deleteById(id);
    }
    
    private ServiceReservationResponseDTO responseDTO(ServiceReservation serviceReservation){
        Reservation reservation = serviceReservation.getReservation();
        AdditionalService additionalService = serviceReservation.getAdditionalService();

       ServiceReservationResponseDTO dto = new ServiceReservationResponseDTO(
        serviceReservation.getIdServiceReservation(),
        serviceReservation.getSubtotal(),
        serviceReservation.getAmount(),
        reservation,
        additionalService
       );

       return dto;
   }
}
