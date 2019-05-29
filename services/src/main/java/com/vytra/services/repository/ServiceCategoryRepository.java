package com.vytra.services.repository;

import com.vytra.services.entity.Company;
import com.vytra.services.entity.services.ServiceCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceCategoryRepository extends CrudRepository<ServiceCategory, Long> {

    List<ServiceCategory> findAllByCompanyAndServiceCategoryIsNull(Company company);
}
