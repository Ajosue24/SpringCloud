package com.vytra.administration.repository.vehicles;

import com.vytra.administration.entity.vehicles.VehicleFeature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleFeatureRepository extends CrudRepository<VehicleFeature,Long> {

}
