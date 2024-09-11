package com.gestionelarca.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionelarca.system.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

    public Hotel findByName(String name);
}
