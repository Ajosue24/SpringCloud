package com.vytra.administration.repository.parametrics;

import com.vytra.administration.entity.parametrics.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City,Long> {
}
