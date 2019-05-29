package com.vytra.administration.service.vehicles;

import com.vytra.administration.entity.vehicles.Fleet;
import com.vytra.administration.repository.vehicles.FleetRepository;
import com.vytra.administration.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class FleetService extends BaseService<Fleet> {
    @Autowired
    FleetRepository repository;

    FleetService(FleetRepository repository) {
        super(repository);
    }


}
