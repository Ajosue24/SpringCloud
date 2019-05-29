package com.vytra.authentication.repository;


import com.vytra.authentication.entity.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface UserRepository extends CrudRepository<User,Long> {


    Optional<User> findByUsername(String userName);
}
