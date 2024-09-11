package com.gestionelarca.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionelarca.system.model.Factura;;

public interface FacturaRepository extends JpaRepository<Factura, Long>{
    
}
