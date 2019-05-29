package com.vytra.administration.service.security;

import com.vytra.administration.entity.security.UserFeature;
import com.vytra.administration.repository.security.UserFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@Service
@Transactional(rollbackFor = Exception.class)
public class UserFeatureService {
    @Autowired
    UserFeatureRepository repository;

    public void saveAll(Set<UserFeature> userFeatureList) {
        repository.saveAll(userFeatureList);
    }
}
