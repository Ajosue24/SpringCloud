package com.vytra.administration.service.insurance;

import com.vytra.administration.entity.insurance.InsuranceType;
import com.vytra.administration.repository.insurance.InsuranceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class InsuranceTypeService {

    @Autowired
    InsuranceTypeRepository repository;

    public Iterable<InsuranceType> getAll() {
        return repository.findAll();
    }

    public InsuranceType saveUpdate(InsuranceType insuranceType) {
        try {
            return repository.save(insuranceType);
        } catch (Exception e) {
            return null;
        }
    }

    public void saveAll(Iterable<InsuranceType> iterable) {
        repository.saveAll(iterable);
    }
}
