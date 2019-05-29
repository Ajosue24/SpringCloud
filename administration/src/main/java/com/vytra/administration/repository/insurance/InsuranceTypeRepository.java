package com.vytra.administration.repository.insurance;

import com.vytra.administration.entity.insurance.InsuranceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceTypeRepository extends CrudRepository<InsuranceType, Long> {
}
