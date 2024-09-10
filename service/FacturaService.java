package com.gestionelarca.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.model.Factura;
import com.gestionelarca.system.repository.FacturaRepository;
import com.gestionelarca.system.service.IService.IFacturaService;

@Service
public class FacturaService implements IFacturaService{
    @Autowired

    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> listFactura() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findFactura(Long id) {
        return facturaRepository.findById(id).orElse(null);

    }

    @Override
    public Factura saveFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public void deleteFactura(Factura factura) {
        facturaRepository.delete(factura);
    }
}
