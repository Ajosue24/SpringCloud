package com.vytra.administration.service.parametrics;

import com.vytra.administration.entity.parametrics.Country;
import com.vytra.administration.repository.parametrics.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CountryService {
    @Autowired
    CountryRepository repository;

    public List<Country> getAll(){
        return (List<Country>) repository.findAll();
    }

}
