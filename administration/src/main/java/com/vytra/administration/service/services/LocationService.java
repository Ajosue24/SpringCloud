package com.vytra.administration.service.services;

import com.vytra.administration.entity.services.Location;
import com.vytra.administration.repository.services.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LocationService {

    @Autowired
    LocationRepository repository;

    public Iterable<Location> getAll() {
        return repository.findAll();
    }

    public Location saveUpdate(Location location) {
        try {
            return repository.save(location);
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Location location) {
        try {
            repository.delete(location);
        } catch (Exception e) {
        }
    }
}
