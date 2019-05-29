package com.vytra.administration.service;

import com.vytra.administration.entity.Status;
import com.vytra.administration.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class StatusService extends BaseService<Status> {

    @Autowired
    StatusRepository repository;

    StatusService(StatusRepository repository) {
        super(repository);
    }
}
