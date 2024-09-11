package com.gestionelarca.system.service.IService;

import java.util.List;

import com.gestionelarca.system.model.ServicioAdicional;

public interface IServicioAdicionalService {
    List<ServicioAdicional> listServicioAdicional();

    ServicioAdicional findServicio(Long id);

    ServicioAdicional saveServicio (ServicioAdicional servicioAdicional);

    void deleteServicio(ServicioAdicional servicioAdicional);
}
