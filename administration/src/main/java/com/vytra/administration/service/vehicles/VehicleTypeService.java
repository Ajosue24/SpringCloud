package com.vytra.administration.service.vehicles;

import com.vytra.administration.entity.vehicles.Vehicle;
import com.vytra.administration.entity.vehicles.VehicleType;
import com.vytra.administration.repository.BaseRepository;
import com.vytra.administration.repository.vehicles.VehicleTypeRepository;
import com.vytra.administration.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleTypeService extends BaseService<VehicleType> {


    @Autowired
    VehicleTypeRepository vehicleTypeRepository;


    VehicleTypeService(VehicleTypeRepository repository) {
        super(repository);
    }
}
