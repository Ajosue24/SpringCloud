package com.vytra.services.repository;

import com.vytra.services.entity.services.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    List<Location> findAllByServiceRequestId_Id(Long id);
}
