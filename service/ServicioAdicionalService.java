package com.gestionelarca.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.model.ServicioAdicional;
import com.gestionelarca.system.repository.ServicioAdicionalRepository;
import com.gestionelarca.system.service.IService.IServicioAdicionalService;

@Service
public class ServicioAdicionalService implements IServicioAdicionalService{
    @Autowired

    private ServicioAdicionalRepository servicioAdicionalRepository;

    @Override
    public List<ServicioAdicional> listServicioAdicional() {
        return servicioAdicionalRepository.findAll();
    }

    @Override
    public ServicioAdicional findServicio(Long id) {
        return servicioAdicionalRepository.findById(id).orElse(null);

    }

    @Override
    public ServicioAdicional saveServicio(ServicioAdicional servicioAdicional) {
        return servicioAdicionalRepository.save(servicioAdicional);
    }

    @Override
    public void deleteServicio(ServicioAdicional servicioAdicional) {
        servicioAdicionalRepository.delete(servicioAdicional);
    }
}
