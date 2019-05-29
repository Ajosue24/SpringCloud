package com.vytra.services.repository;

import com.vytra.services.entity.services.ServiceRequestHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRequestHistoryRepository extends CrudRepository<ServiceRequestHistory, Long>{


}
