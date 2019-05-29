package com.vytra.administration.repository.parametrics;

import com.vytra.administration.entity.parametrics.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country,Long> {
}
