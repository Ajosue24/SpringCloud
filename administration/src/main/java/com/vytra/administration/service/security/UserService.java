package com.vytra.administration.service.security;

import com.vytra.administration.entity.security.User;
import com.vytra.administration.repository.security.UserRepository;
import com.vytra.administration.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends BaseService<User> {
    @Autowired
    UserRepository repository;

    UserService(UserRepository repository) {
        super(repository);
    }


}