package com.vytra.administration.service.security;

import com.vytra.administration.entity.security.Roles;
import com.vytra.administration.repository.security.RoleRepository;
import com.vytra.administration.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleService extends BaseService<Roles> {

    @Autowired
    RoleRepository repository;

    RoleService(RoleRepository repository) {
        super(repository);
    }

   public void test(){
        repository.findAll();
    }
}
