package com.vytra.services.repository;

import com.vytra.services.entity.Company;
import com.vytra.services.entity.security.User;
import com.vytra.services.entity.services.ServiceRequest;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends CrudRepository<ServiceRequest,Long> {

    List<ServiceRequest> findAllByUserIdAndCompany(User user, Company company);
}
