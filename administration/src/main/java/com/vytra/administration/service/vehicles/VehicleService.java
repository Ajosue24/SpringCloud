package com.vytra.administration.service.vehicles;

import com.vytra.administration.entity.vehicles.Fleet;
import com.vytra.administration.entity.vehicles.Vehicle;
import com.vytra.administration.repository.vehicles.FleetRepository;
import com.vytra.administration.repository.vehicles.VehicleRepository;
import com.vytra.administration.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleService extends BaseService<Vehicle> {
    @Autowired
    VehicleRepository repository;
    @Autowired
    FleetRepository fleetRepository;


    VehicleService(VehicleRepository repository) {
        super(repository);
    }

    public List<Vehicle> listFleet (Long id){
        Optional<Fleet> fleet = fleetRepository.findById(id);
        return repository.findAllByFleetSet(fleet.get());
    }

    public void saveAll(List<Vehicle> vehiclesList){
         repository.saveAll(vehiclesList);
    }
}
