package com.vytra.services.services;

import com.vytra.services.entity.Company;
import com.vytra.services.entity.services.ServiceCategory;
import com.vytra.services.repository.ServiceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ServiceCategoryService {

    @Autowired
    ServiceCategoryRepository repository;

    public List<ServiceCategory> getAllParents(String filter){
        //TODO get id company from token
        //TODO: consultar la compa;ia con el token de usuario logueado en caso de que sea administrador que traiga todo
        Company company = new Company();
        company.setId(1L);
        return repository.findAllByCompanyAndServiceCategoryIsNull(company);
    }


}
