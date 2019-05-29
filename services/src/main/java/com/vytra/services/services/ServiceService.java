package com.vytra.services.services;

import com.vytra.services.entity.services.Service;
import com.vytra.services.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional(rollbackFor = Exception.class)
public class ServiceService{

    @Autowired
    ServiceRepository repository;

    public List<Service> getAllByServiceCategory(long id){
      return repository.findAllByServiceCategory_Id(id);
    }


}
