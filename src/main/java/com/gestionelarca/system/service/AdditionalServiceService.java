package com.gestionelarca.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionelarca.system.model.AdditionalService;
import com.gestionelarca.system.repository.AdditionalServiceRepository;
import com.gestionelarca.system.service.IService.IAdditionalServiceService;

@Service
public class AdditionalServiceService implements IAdditionalServiceService{
    @Autowired

    private AdditionalServiceRepository additionalServiceRepository;

    @Override
    public List<AdditionalService> listAdditionalService() {
        return additionalServiceRepository.findAll();
    }

    @Override
    public AdditionalService findService(Long id_Service) {
        return additionalServiceRepository.findById(id_Service).orElse(null);

    }

    @Override
    public AdditionalService saveService(AdditionalService additionalService) {
        return additionalServiceRepository.save(additionalService);
    }

    @Override
    public void deleteService(AdditionalService additionalService) {
        additionalServiceRepository.delete(additionalService);
    }
}
