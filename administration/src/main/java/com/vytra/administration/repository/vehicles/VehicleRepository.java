package com.vytra.administration.repository.vehicles;

import com.vytra.administration.entity.vehicles.Fleet;
import com.vytra.administration.entity.vehicles.Vehicle;
import com.vytra.administration.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends BaseRepository<Vehicle> {

    List<Vehicle> findAllByFleetSet(Fleet fleet);
}
