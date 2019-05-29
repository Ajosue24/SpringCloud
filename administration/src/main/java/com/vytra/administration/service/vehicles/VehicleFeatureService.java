package com.vytra.administration.service.vehicles;

import com.vytra.administration.entity.vehicles.VehicleFeature;
import com.vytra.administration.repository.vehicles.VehicleFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleFeatureService {
    @Autowired
    VehicleFeatureRepository repository;

    public void save(VehicleFeature vehicleFeature) throws Exception {
        this.repository.save(vehicleFeature);
    }

    public void saveAll(Set<VehicleFeature> vehicleFeatureList) {
        repository.saveAll(vehicleFeatureList);
    }
}
