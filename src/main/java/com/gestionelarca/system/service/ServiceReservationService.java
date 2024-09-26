package com.gestionelarca.system.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.DTO.ServiceReservationDTO;
import com.gestionelarca.system.DTO.ServiceReservationResponseDTO;
import com.gestionelarca.system.DTO.ServiceReservationSaveDTO;
import com.gestionelarca.system.model.AdditionalService;
import com.gestionelarca.system.model.Event;
import com.gestionelarca.system.model.Room;
import com.gestionelarca.system.model.ServiceReservation;
import com.gestionelarca.system.repository.ServiceReservationRepository;
import com.gestionelarca.system.service.IService.IServiceReservationService;

@Service
public class ServiceReservationService implements IServiceReservationService {

    @Autowired
    ServiceReservationRepository serviceReservationRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    EventService eventService;

    @Autowired
    AdditionalServiceService additionalServiceService;

    @Override
    public List<ServiceReservationResponseDTO> listByAdditionalService(Long id_Service) {
        AdditionalService additionalService = additionalServiceService.findService(id_Service);
        List<ServiceReservation> lServiceReservations = serviceReservationRepository.findByAdditionalService(additionalService);

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
            
            Room room = roomService.getRoom(serviceReservationSaveDTO.getId_room());
            Event event = eventService.getEvent(serviceReservationSaveDTO.getId_event());
            AdditionalService additionalService = additionalServiceService.findService(serviceReservationSaveDTO.getId_Service());
            
            ServiceReservation serviceReservation = new ServiceReservation(
                null,
                room,
                event,
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

            // BUSCAR HABITACIÓN, EVENTO Y SERVICIO ADICIONAL
            Room room = roomService.getRoom(serviceReservationDTO.getId_room());
            Event event = eventService.getEvent(serviceReservationDTO.getId_event());
            AdditionalService additionalService = additionalServiceService.findService(serviceReservationDTO.getId_Service());

            serviceReservation.setAmount(serviceReservationDTO.getAmount());
            serviceReservation.setSubtotal(serviceReservationDTO.getSubtotal());
            serviceReservation.setRoom(room);
            serviceReservation.setEvent(event);
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
        Room room = serviceReservation.getRoom();
        Event event = serviceReservation.getEvent();
        AdditionalService additionalService = serviceReservation.getAdditionalService();

       ServiceReservationResponseDTO dto = new ServiceReservationResponseDTO(
        serviceReservation.getIdServiceReservation(),
        serviceReservation.getSubtotal(),
        serviceReservation.getAmount(),
        room,
        event,
        additionalService
       );

       return dto;
   }
}
