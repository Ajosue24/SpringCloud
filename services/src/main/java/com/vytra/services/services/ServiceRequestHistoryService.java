package com.vytra.services.services;

import com.vytra.services.entity.services.ServiceRequestHistory;
import com.vytra.services.repository.ServiceRequestHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ServiceRequestHistoryService {

    @Autowired
    ServiceRequestHistoryRepository serviceRequestHistoryRepository;


    public void save(ServiceRequestHistory serviceRequestHistory) throws Exception{
        this.serviceRequestHistoryRepository.save(serviceRequestHistory);
    }
}
