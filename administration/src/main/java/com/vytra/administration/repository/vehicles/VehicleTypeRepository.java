package com.vytra.administration.repository.vehicles;

import com.vytra.administration.entity.security.Roles;
import com.vytra.administration.entity.vehicles.VehicleType;
import com.vytra.administration.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends BaseRepository<VehicleType> {
}
