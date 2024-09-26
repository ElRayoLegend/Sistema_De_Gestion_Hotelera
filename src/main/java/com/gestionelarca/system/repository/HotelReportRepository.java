package com.gestionelarca.system.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionelarca.system.model.Hotel;
import com.gestionelarca.system.model.HotelReport;

public interface HotelReportRepository extends JpaRepository<HotelReport, Long> {
    List<HotelReport> findByHotel(Hotel hotel);
}