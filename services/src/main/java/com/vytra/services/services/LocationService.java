package com.vytra.services.services;

import com.vytra.services.entity.services.Location;
import com.vytra.services.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class LocationService {

    @Autowired
   private LocationRepository repository;

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

    public List<Location> findAllByServiceRequestId(Long id){
            return repository.findAllByServiceRequestId_Id(id);
    }

    public void removeLocationList(List<Location> locationList)throws Exception{
        repository.deleteAll(locationList);

    }
}
