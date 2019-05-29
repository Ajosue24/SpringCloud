package com.vytra.administration.repository.parametrics;

import com.vytra.administration.entity.parametrics.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State,Long> {
}
