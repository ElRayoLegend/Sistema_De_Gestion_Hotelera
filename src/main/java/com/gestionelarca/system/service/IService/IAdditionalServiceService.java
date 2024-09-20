package com.gestionelarca.system.service.IService;

import java.util.List;

import com.gestionelarca.system.model.AdditionalService;

public interface IAdditionalServiceService {
    List<AdditionalService> listAdditionalService();

    AdditionalService findService(Long id_Service);

    AdditionalService saveService (AdditionalService additionalService);

    void deleteService(AdditionalService additionalService);
}
