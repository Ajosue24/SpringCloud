package com.vytra.administration.service.insurance;

import com.vytra.administration.entity.insurance.InsurerInsuranceType;
import com.vytra.administration.repository.insurance.InsurerInsuranceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class InsurerInsuranceTypeService {

    @Autowired
    InsurerInsuranceTypeRepository repository;

    public Iterable<InsurerInsuranceType> getAll() {
        return repository.findAll();
    }

    public InsurerInsuranceType saveUpdate(InsurerInsuranceType insurer_insuranceType) {
        try {
            return repository.save(insurer_insuranceType);
        } catch (Exception e) {
            return null;
        }
    }

    public void saveAll(Iterable<InsurerInsuranceType> iterable) {
        repository.saveAll(iterable);
    }
}

