package com.vytra.administration.repository.security;

import com.vytra.administration.entity.security.Roles;
import com.vytra.administration.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Roles> {
}
