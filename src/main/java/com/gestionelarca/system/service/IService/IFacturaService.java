package com.gestionelarca.system.service.IService;

import java.util.List;

import com.gestionelarca.system.model.Factura;

public interface IFacturaService {
    List<Factura> listFactura();

    Factura findFactura(Long id);

    Factura saveFactura (Factura factura);

    void deleteFactura(Factura factura);
}
