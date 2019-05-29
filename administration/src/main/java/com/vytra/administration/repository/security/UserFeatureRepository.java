package com.vytra.administration.repository.security;

import com.vytra.administration.entity.security.UserFeature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeatureRepository  extends CrudRepository<UserFeature,Long> {
}
