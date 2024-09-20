package com.gestionelarca.system.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.DTO.ReservationResponseDTO;
import com.gestionelarca.system.DTO.ReservationSaveDTO;
import com.gestionelarca.system.DTO.UserClearDTO;
import com.gestionelarca.system.model.Event;
import com.gestionelarca.system.model.Reservation;
import com.gestionelarca.system.model.Room;
import com.gestionelarca.system.model.User;
import com.gestionelarca.system.repository.ReservationRepository;
import com.gestionelarca.system.service.IService.IReservationService;

@Service
public class ReservationService implements IReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @Autowired
    EventService eventService;

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
    public void deleteReservation(Reservation reservation){
        reservationRepository.delete(reservation);
    }

    // Los tres metodos que se agregaron
    @Override
    public List<ReservationResponseDTO> myReservations(Long userId) {
        User user = userService.getUser(userId);
        List<Reservation> reservations = reservationRepository.findByUser(user);
        return reservations
            .stream()
            .map(reservation -> responseDTO(reservation))
            .collect(Collectors.toList());
    }
    
    @Override
    public Reservation findByIdReservation(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation save(ReservationSaveDTO reservationDTO){
            //Convertir la fecha que llega en STRING (LocalDateTime) a TIMESTAMP
            Timestamp date_startDate = Timestamp.valueOf(reservationDTO.getDate_start());
            //Convertir la fecha que llega en STRING (LocalDateTime) a TIMESTAMP
            Timestamp date_endDate = Timestamp.valueOf(reservationDTO.getDate_end());
            //Obtenemos el usuario completo que viene en la solicitud (userId)
            User user = userService.getUser(reservationDTO.getUserId());
            //Obtenemos el evento completo que viene en la solicitud (eventId)
            Event event = eventService.getEvent(reservationDTO.getEventId());
            //Obtenemos la habitacion completa que viene en la solicitud (roomId)
            Room room = roomService.getRoom(reservationDTO.getRoomId());
            Reservation reservation = new Reservation(
                null,
                date_startDate,
                date_endDate,
                reservationDTO.getStatus(),
                user,
                room,
                event        
            );
            return reservationRepository.save(reservation);
    }

    private ReservationResponseDTO responseDTO(Reservation reservation){
        User user = reservation.getUser();
        UserClearDTO userDTO = new UserClearDTO(
            user.getName(),
            user.getSurname(),
            user.getSurname()
        );

        ReservationResponseDTO dto = new ReservationResponseDTO(
            reservation.getId_Reservation(),
            reservation.getDate_start(),
            reservation.getDate_end(),
            reservation.getStatus(),
            userDTO,
            reservation.getRoom(),
            reservation.getEvent()
        );

        return dto;
    }

}
