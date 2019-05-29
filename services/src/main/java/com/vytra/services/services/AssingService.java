package com.vytra.services.services;

import com.vytra.services.entity.security.User;
import com.vytra.services.entity.services.ServiceRequest;
import com.vytra.services.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AssingService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    /**
     * Metodo que validara y seleccionara un conductor o a quien puede resolver la peticion del usuario
     */
    public void assignService(ServiceRequest serviceRequest){
        try {
            serviceRequest.setResponsibleId(new User(2l));
            serviceRequestRepository.save(serviceRequest);
        }catch (Exception e){
            //Notificar que hay un error al guardar o asignar un usuario para que atienda el servicio
            e.printStackTrace();
        }
    }




}
