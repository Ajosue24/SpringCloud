package com.vytra.administration.service;

import com.vytra.administration.entity.Cost;
import com.vytra.administration.repository.CostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostService extends BaseService<Cost> {

    @Autowired
    CostRepository repository;

    CostService(CostRepository repository) {
        super(repository);
    }

}
