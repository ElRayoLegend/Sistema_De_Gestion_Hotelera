package com.gestionelarca.system.service.IService;

import java.util.List;

import com.gestionelarca.system.model.HotelReport;

public interface IHotelReportService {
    List<HotelReport> listHotelReport();

    HotelReport findHotelReport(Long id);
    
    HotelReport saveHotelReport(HotelReport hotelReport);

    void deleteHotelReport(HotelReport hotelReport);
}
