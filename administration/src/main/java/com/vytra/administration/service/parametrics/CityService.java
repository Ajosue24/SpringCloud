package com.vytra.administration.service.parametrics;

import com.vytra.administration.entity.parametrics.City;
import com.vytra.administration.repository.parametrics.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CityService {
    @Autowired
    CityRepository repository;

    public List<City> getAll (){
        return (List<City>) repository.findAll();
    }
}
