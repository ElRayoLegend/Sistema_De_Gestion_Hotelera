package com.gestionelarca.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionelarca.system.model.AdditionalService;

public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long> {
    
}
