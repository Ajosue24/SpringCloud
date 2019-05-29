package com.vytra.administration.service.services;

import com.vytra.administration.entity.services.Service;
import com.vytra.administration.repository.services.ServiceRepository;
import com.vytra.administration.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional(rollbackFor = Exception.class)
public class ServiceService extends BaseService<Service> {

    @Autowired
    ServiceRepository repository;

    ServiceService(ServiceRepository repository) {
        super(repository);
    }

}
