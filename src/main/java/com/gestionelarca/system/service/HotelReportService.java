package com.gestionelarca.system.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.DTO.HotelReportDTO;
import com.gestionelarca.system.model.Hotel;
import com.gestionelarca.system.model.HotelReport;
import com.gestionelarca.system.repository.HotelReportRepository;
import com.gestionelarca.system.service.IService.IHotelReportService;

@Service
public class HotelReportService implements IHotelReportService {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelReportRepository hotelRepository;

    @Override
    public List<HotelReport> listHotelReport() {
        return hotelRepository.findAll();
    }

    @Override
    public HotelReport findHotelReport(Long id) {
        return hotelRepository.findById(id).orElse(null);

    }

    @Override
    public HotelReport saveHotelReport(HotelReportDTO hotelReportDTO) {
        try {
            Timestamp dateReport = Timestamp.valueOf(hotelReportDTO.getDate());

            Hotel hotel = hotelService.getHotel(hotelReportDTO.getHotelId());
            if (hotel == null) {
                throw new IllegalArgumentException("Hotel no encontrado con ID: " + hotelReportDTO.getHotelId());
            }

            HotelReport hotelReport = new HotelReport(
                null,
                hotelReportDTO.getTotal_reservations(),
                hotelReportDTO.getTotal_occupied_rooms(),
                hotelReportDTO.getTotal_revenue(),
                dateReport,
                hotel
            );

            return hotelRepository.save(hotelReport);
        } catch (IllegalArgumentException e) {
            // Captura errores específicos
            System.err.println("Error: " + e.getMessage());
            throw e; // Re-lanza la excepción
        } catch (Exception err) {
            // Captura excepciones generales
            System.err.println("Error en saveHotelReport: " + err.getMessage());
            err.printStackTrace(); // Imprimir el stack trace
            throw new IllegalArgumentException("Error al guardar el informe del hotel", err);
        }
    }

    @Override
    public void deleteHotelReport(HotelReport hotelReport) {
        hotelRepository.delete(hotelReport);
    }
}
