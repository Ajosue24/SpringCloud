package com.vytra.administration.repository.insurance;

import com.vytra.administration.entity.insurance.InsurerInsuranceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurerInsuranceTypeRepository extends CrudRepository<InsurerInsuranceType, Long> {
}
