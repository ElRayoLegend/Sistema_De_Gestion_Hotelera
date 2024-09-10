package com.gestionelarca.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.model.HotelReport;
import com.gestionelarca.system.repository.HotelReportRepository;
import com.gestionelarca.system.service.IService.IHotelReportService;

@Service
public class HotelReportService implements IHotelReportService {
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
    public HotelReport saveHotelReport(HotelReport hotelReport) {
        return hotelRepository.save(hotelReport);
    }

    @Override
    public void deleteHotelReport(HotelReport hotelReport) {
        hotelRepository.delete(hotelReport);
    }
}
