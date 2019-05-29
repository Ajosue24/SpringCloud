package com.vytra.administration.service.services;

import com.vytra.administration.entity.services.ServiceRequest;
import com.vytra.administration.repository.services.ServiceRequestRepository;
import com.vytra.administration.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ServiceRequestService extends BaseService<ServiceRequest> {

    @Autowired
    ServiceRequestRepository repository;

    ServiceRequestService(ServiceRequestRepository repository) {
        super(repository);
    }

}
