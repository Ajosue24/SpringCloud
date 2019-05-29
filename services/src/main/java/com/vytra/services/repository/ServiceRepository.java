package com.vytra.services.repository;

import com.vytra.services.entity.services.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<Service,Long> {

    List<Service> findAllByServiceCategory_Id(Long id);


}
