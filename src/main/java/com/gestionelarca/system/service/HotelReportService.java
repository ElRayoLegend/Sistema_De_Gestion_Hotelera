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
            Hotel hotel = hotelService.getHotel(hotelReportDTO.getHotelId());
            LocalDateTime dateTime = hotelReportDTO.getDate().atStartOfDay();
            Timestamp dateReport = Timestamp.valueOf(dateTime);
            HotelReport hotelReport = new HotelReport(
                null,
                hotelReportDTO.getTotal_reservations(),
                hotelReportDTO.getTotal_occupied_rooms(),
                hotelReportDTO.getTotal_revenue(),
                dateReport,
                hotel
            );
            return hotelRepository.save(hotelReport);
        } catch (Exception err) {
            throw new IllegalArgumentException("Error al parsear las fechas", err);
        }
    }

    @Override
    public void deleteHotelReport(HotelReport hotelReport) {
        hotelRepository.delete(hotelReport);
    }
}
